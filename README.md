# Nerdfactor Spring Utils

Some common utility features used by nerdfactor Spring Boot applications.

### Enum Util

- Turn enum values into an array of strings.

### Hash Util

- Hash a string with sha256.

### Message Util

- Add a message in different levels to RedirectAttributes.
- Add a message in different levels to ViewModel.

### View Util

- Different utilities in the context of a Thymeleaf view.
- Provide the current request Uri and allow to append a suffix.
- Append sortBy parameters to the current request Uri.
- Append a class value on a condition.
- Check if the current user is in one role within a list of roles.
- Add a status value to a html element.

### Property Logging

- Log currently used properties on server startup. Log level can be configured. Default is OFF.

```yaml
logging:
  level:
    eu.nerdfactor.springutils.PropertyLogging: INFO
```


## Installation

```xml

<dependency>
    <groupId>com.github.nerdfactor</groupId>
	<artifactId>springutils</artifactId>
    <version>0.5.0</version>
</dependency>
```

The library is published using jitpack.io registry. Add jitpack to the
projects repositories.

```xml

<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```