cd ..
mvn clean install
java -Xverify:none -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv6Addresses=false -Djava.security.egd=file:/dev/./urandom -jar target/app-0.0.1-SNAPSHOT.jar --server.port=443 --server.port.http=80 &