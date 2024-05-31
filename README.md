# **CYCL**🌳🚲

CYCL is an innovative application designed to facilitate the rental and usage of bicycles and scooters. The app integrates Firebase for authentication and real-time database interactions, providing a seamless experience for users to rent, ride, and return vehicles safely.

## Table of Contents

- [Features](#features)
- [External Sensor Integration](#external-sensor-integration)
  - [ESP8266 and Ultrasonic Sensor](#esp8266-and-ultrasonic-sensor)
  - [How It Works](#how-it-works)
- [Screenshots](#screenshots)
  - [Splash Screen](#splash-screen)
  - [Login Screen](#login-screen)
  - [Signup Screen](#signup-screen)
  - [Homepage](#homepage)
  - [Maps Screen](#maps-screen)
  - [Ride Screen](#ride-screen)
  - [Park Screen](#park-screen)
  - [Payment Screen](#payment-screen)
- [Video Demonstration](#video-demonstration)
- [Usage](#usage)
- [Technologies Used](#technologies-used)
- [Contributors](#contributors)

## Features

- **Splash Screen**
  - Animated splash screen with rotating C's imitating bicycle wheels moving.
  - Duration: 1.5 seconds.

- **Login and Signup Screens**
  - Firebase authentication for secure login and signup.
  - Validates user credentials and navigates to the homepage upon successful login.

- **Homepage**
  - Animated swipe navigation using Motion Layout.
  - Options to hire a bike or scooter, view the map, and logout.

- **Maps Screen**
  - Displays user location and nearby rental stations.
  - QR code scanner to start the trip.

- **Ride Screen**
  - Active throughout the trip with a timer and animated ring.
  - Return vehicle button to end the trip and navigate to the park activity.

- **Park Screen**
  - Uses ultrasonic sensor data to assist in parking.
  - Interactive feedback through colors, animations, vibrations, and text changes.
  - Navigate to the payment screen upon successful parking.

- **Payment Screen**
  - Displays payment information and allows users to rate their experience.
  - Animated background and navigation back to the homepage.

## External Sensor Integration

### ESP8266 and Ultrasonic Sensor

The CYCL app integrates an ESP8266 microcontroller with an ultrasonic sensor to assist users during the parking phase. This setup helps users avoid obstacles and park the vehicle safely by providing real-time feedback through the app.

#### How It Works

1. **Ultrasonic Sensor Setup**:
   - The ultrasonic sensor is connected to the ESP8266 microcontroller.
   - The sensor measures the distance to the nearest obstacle behind the vehicle.

2. **Data Transmission**:
   - The ESP8266 sends the distance data to Firebase Realtime Database.
   - The data is continuously updated to reflect the current distance.

3. **App Interaction**:
   - The CYCL app retrieves the distance data from Firebase in real-time.
   - The app provides visual and haptic feedback based on the distance:
     - **Visual**: The parking screen displays the distance and changes color as the vehicle gets closer to an obstacle.
     - **Haptic**: The phone vibrates more intensely as the vehicle approaches an obstacle.

## Screenshots

### Splash Screen
![Splash Screen](https://github.com/Bishoylabib/Cycl/assets/65047880/21e07746-62a3-4f67-8acc-ce1119f2370a)

### Login Screen
![Login Screen](https://github.com/Bishoylabib/Cycl/assets/65047880/9408ff2c-88d7-4c95-a482-81906f7425ae)

### Signup Screen
![Signup Screen](https://github.com/Bishoylabib/Cycl/assets/65047880/9044cf7c-3e72-4271-8c03-217fbc644da8)

### Homepage
![Homepage](https://github.com/Bishoylabib/Cycl/assets/65047880/c0dea2a1-428b-4230-adb3-58bf3e3b396c)

### Maps Screen
![Maps Screen](https://github.com/Bishoylabib/Cycl/assets/65047880/985b3ee7-4870-43b5-9cf7-a20edad460d9)

### Ride Screen
![Ride Screen](https://github.com/Bishoylabib/Cycl/assets/65047880/b9285c46-b385-4bcf-b868-8896cea6e42c)

### Park Screen
![Park Screen](https://github.com/Bishoylabib/Cycl/assets/65047880/680653c9-eef8-47cd-a428-aa8e5b8be60f)

### Payment Screen
![Payment Screen](https://github.com/Bishoylabib/Cycl/assets/65047880/5efff0ed-1cb3-4e4f-a043-e981ad31f35f)

## Video Demonstration

For a detailed overview of the CYCL app in action, watch our [video demonstration](https://github.com/Bishoylabib/Cycl/assets/65047880/aa253099-a0dd-4500-8d23-93797aa78f83).

## Usage

1. Open the application.
2. Sign up or log in using your email and password.
3. Navigate through the homepage to hire a bike or scooter.
4. Use the maps to find and rent a vehicle.
5. Start your ride and monitor your trip through the ride screen.
6. Park the vehicle using the park screen guidance.
7. Complete the trip and payment process.

## Technologies Used

- **Firebase Authentication**: Secure login and signup.
- **Firebase Realtime Database**: Storing and retrieving real-time data.
- **Motion Layout**: For animations and smooth UI transitions.
- **Android**: The primary platform for development.
- **ESP8266**: Microcontroller used for sensor data collection.
- **Ultrasonic Sensor**: Measures distance to assist in parking.

## Contributors

- Bishoy Labib
