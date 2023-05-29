FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Install the required dependencies
RUN apk add --no-cache \
    git \
    wget \
    unzip

# Download and set up the Android SDK
ENV ANDROID_SDK_ROOT /opt/android-sdk
RUN mkdir -p ${ANDROID_SDK_ROOT}
RUN wget -O /tmp/sdk-tools.zip https://dl.google.com/android/repository/commandlinetools-linux-7302050_latest.zip
RUN unzip /tmp/sdk-tools.zip -d ${ANDROID_SDK_ROOT}/cmdline-tools
RUN mv ${ANDROID_SDK_ROOT}/cmdline-tools ${ANDROID_SDK_ROOT}/cmdline-tools/latest

# Add the Android SDK components
ENV PATH ${PATH}:${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin
RUN yes | sdkmanager "platform-tools" "build-tools;30.0.3" "platforms;android-33" "extras;google;google_play_services" "extras;android;m2repository" "extras;google;m2repository"

# Install Fastlane
RUN apk add --no-cache ruby ruby-dev build-base
RUN gem install fastlane

# Cache dependencies
COPY build.gradle.kts settings.gradle.kts /app/
COPY app/build.gradle.kts /app/app/
RUN gradle androidDependencies

# Copy the project files
COPY . /app

# Build the Android App Bundle
RUN ./gradlew bundleRelease

# Deploy to Firebase and Google Play
RUN fastlane init
RUN fastlane supply init
RUN fastlane firebase_app_distribution
RUN fastlane supply