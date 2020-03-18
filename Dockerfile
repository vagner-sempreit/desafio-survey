FROM openjdk:11
VOLUME /tmp
ADD target/account-beneficiary.jar account-beneficiary.jar
RUN bash -c 'touch /account-beneficiary.jar'
