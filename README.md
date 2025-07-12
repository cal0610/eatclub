# Eat club technical round 2 challenge

# Development

## Profile
Run the app under `local` profile.

```
-Dspring.profiles.active=local
``` 

## Lombok

Enable annotation processing.

![test](lombok.png)

# Coding best practices
- Constructor dependency injection rather than @Autowired
- Lombok to simplify boilerplate code
- Separation of DTO and domain classes
- Immutability using records
- Implementing interfaces for both mappers, and time parser for extensibility and flexibility in future changes
- Unit tests, sliced testing i.e webmvc
- Centralized exception handling
- RFC 7807 specification for problem responses 
- spring profiles and cascading properties

# Recommended improvements

- Integration test with mock server for restaurant client
- Consider making restaurant an interface and use generics for greater flexibility