FROM esolang/kotlin:latest 

COPY . /var/www/restaurant/api

WORKDIR /var/www/restaurant/api
EXPOSE 4566

CMD ["./gradlew", "up"]