# Social Media Meetex REST API 

I wanted to showcase my growth as a developer, so I decided to take on a familiar topic from my past but elevate it to a higher coding standard. For reference, see my previous work on this topic at [Meetex Desktop App](https://github.com/cyboranf/meetex-desktop-app). </br>
In this advanced version of 'Meetex', I've integrated numerous features and concepts I've gleaned from my books and tutorials. 'Meetex' stands as a testament to my continuous learning and the versatility of modern backend technologies.


## Features
- **User Authentication**: Uses Spring Security integrated with JWT.
- **Profile Management**: Users can create, update, and personalize their profiles with a profile picture, bio, and more.
- **Chat System**: Directly chat with other users
- **Posts**: Users can create, edit, and delete posts. They can also attach images, videos, and links to their posts.
- **Comments**: Engage in posts by commenting. Edit and delete comments seamlessly.
- **Reactions**: React to posts and comments with a variety of emojis to express feelings.
- **Notifications**: Real-time notifications for likes, comments, new followers, and chat messages.

## Default Credentials
| Role         | Username| Password |
|--------------|----------|----------|
|     USER     |   User   | User     |
| MOD         | Mod      | Mod  |
|    ADMIN     | Admin    | Admin    |

## Inspiration
The main spark for this project came from an earlier application I developed. When I looked back at my previous **'Meetex'** desktop app, I felt a push to revisit the concept and take it to the next level. At the same time, I observed the features and designs of some popular social media apps out there. This made me realize that creating a social media platform is a great way to display a wide range of skills.

## ERD

![ERD](/images/erd.png)

## Swagger Documentation

![Swagger Documentation](/images/swaggerDocumentation.png)

To access the Swagger UI for this application, run the application and navigate to `http://localhost:8080/swagger-ui.html` in your web browser.

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository to your local machine.
2. Make sure you have JDK and Maven installed on your system.
3. Navigate to the project root directory and run `mvn clean install` to build the project.
4. Run the application using `mvn spring-boot:run` or by executing the main class `com.example.meetexApi.MeetexApiApplication` in your IDE.
5. Access the application on `http://localhost:8080/`.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1. Fork the repository.
2. Create a branch with a descriptive name that represents the changes you want to make.
3. Commit your changes to the branch and push them to your fork.
4. Open a pull request to merge your changes into the main repository.
