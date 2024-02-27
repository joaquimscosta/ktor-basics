# Introduction to Ktor and Routing Plugin

This project serves as a basic introduction to Ktor, a Kotlin-based asynchronous framework used for creating microservices, web applications, and more. It also covers the usage of the Routing plugin, a fundamental part of Ktor that allows us to define the endpoints of our application.

## Prerequisites

To get started with this project, you need to have the following installed on your machine:

- JDK 21
- IntelliJ IDEA (Recommended)
- Gradle

Clone this repository to your local machine and open it in your IDE.

## Building and Running the Project

This project uses Gradle as a build tool. To build the project, navigate to the project directory in your terminal and run the following command:

```bash
./gradlew build
```

This will start the Ktor server on http://localhost:8081.

## What is Ktor?
Ktor is a Kotlin-based framework for building asynchronous servers and clients. It is a lightweight, easy-to-use framework that is highly extensible and can be used to build a wide range of applications, including web applications, microservices, and more.

It can be used to write rest services for Android application, or multi platform (KMP).

You can create a Ktor application by using the wizard in this website, add plugins 
https://start.ktor.io/

## How to configure Ktor application
### Code based configuration
Application.kt
```kotlin
fun main(args: Array<String>) {
  embeddedServer(
      Netty,
      port = 8080,
      host = "0.0.0.0",
      module = Application::module
  ).start(wait = true)
}

fun Application.module() {...}
```

### File based configuration
resources/application.conf
```conf
ktor {
  deployment {
    port = 8081
    port = ${?PORT}
  }
  development = true
  application {
    modules = [com.costacodecraft.ApplicationKt.module]
  }
}
```
Application.kt
```kotlin
fun main(args: Array<String>) {
  EngineMain.main(args)
}

fun Application.module() {...}
```
## Routing plugin
Routing is a feature that allows you to define the endpoints of your application. It is a fundamental part of Ktor and is used to define the routes that your application will respond to.
To configure the routing plugin you normally add an extension function to the Application class, and then you can define the routes in the body of the function.

```kotlin
fun Application.module(){
  routing{
      // routes defined here
  }
}
```
### Define a handler (verb, path pattern and handler)
```kotlin
routing{
  get("/users"){
    handle{
      call.respondText("Get all users!!")
    }
  }
}
```
Specify the verb as part of the DSL: `post`, `get`, `put`, `delete`, `head`,...

### Pattern
- `/users/{id}`
- `/users/{id}/friends`
- `/users/{id}/friends/{friendId}`
- You can use wildcards: /books/*
  - matches: `/books/1`, `/books/code_complete`, but not `/books`
- You can use tailcards: `/books/{...}` if you need more flexibility
  - matches: `/books`, `/books/1`, `/books/code_complete`

### Handler
 - The code that will be executed when the route is matched
 - Has access to the `call` object
 - Read the request, write the response
  
### `call` Object
 - Represents the request and response
 - Has properties and methods to read the request and write the response
 - Query parameters `call.request.queryParameters["name"]`

## License
This project is licensed under the MIT License. See the LICENSE file for details.
