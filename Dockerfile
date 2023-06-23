FROM ubuntu:latest

ENV ANDROID_HOME="/usr/local/android-sdk"
ENV ANDROID_SDK_ROOT="/usr/local/android-sdk"
ENV PATH="$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools:$PATH"

RUN apt-get update && \
    apt-get install -y openjdk-11-jdk wget unzip git

RUN mkdir -p ${ANDROID_HOME} && \
    wget https://dl.google.com/android/repository/commandlinetools-linux-7583922_latest.zip -O /tmp/sdk-tools.zip && \
    unzip /tmp/sdk-tools.zip -d ${ANDROID_HOME}/cmdline-tools && \
    rm /tmp/sdk-tools.zip

RUN mkdir -p ${ANDROID_HOME}/cmdline-tools/tools && \
    mv ${ANDROID_HOME}/cmdline-tools/bin ${ANDROID_HOME}/cmdline-tools/tools

RUN yes | sdkmanager --sdk_root=${ANDROID_HOME} --licenses && \
    sdkmanager --sdk_root=${ANDROID_HOME} "platforms;android-33" "build-tools;33.0.0" "platform-tools" "extras;google;google_play_services"

RUN apt-get install -y ruby-full
RUN gem install fastlane

VOLUME ["/app"]
WORKDIR /app

CMD ["tail", "-f", "/dev/null"]