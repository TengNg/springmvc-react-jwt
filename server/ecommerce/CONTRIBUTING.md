## Notes

- Build/Compile: using jdk-11

- For HibernateConfiguration: Create "src/main/resources" then create database1.properties (setup - see example below), check @PropertySource in src/main/java/com/ndt/configs/HibernateConfig.java

- For HibernateConfiguration: Create "src/main/resources" then create configs.properties (setup - see example below), check @PropertySource in src/main/java/com/ndt/configs/HibernateConfig.java

Ex: (in database1.properties)

```
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.showSql=true
hibernate.connection.driverClass=com.mysql.cj.jdbc.Driver
hibernate.connection.url=jdbc:mysql://localhost:3306/<your_database_name>
hibernate.connection.username=<your_database_username>
hibernate.connection.password=<your_database_password>
```


Ex: (in configs.properties)

```
cloudinary.cloud_name=<your_cloud_name>
cloudinary.api_id=<your_api_id>
cloudinary.api_secret=<your_api_secret>
```
