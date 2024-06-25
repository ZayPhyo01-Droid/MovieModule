
# Movie Module

A sample Jetpack compose movie app with multi module style + mvvm clean arch and using baseline profile to optimze app start up.

<img src = "https://github.com/ZayPhyo01-Droid/MovieModule/blob/main/screenshot/Screenshot_20240626_002645.png" width = "240" height = "510"/>

## Benchmarking
   There are 3 compilation mode to benchmarking the app start 
   - No baseline
   - With Baseline Mode
   - Full Module

<img src = "https://github.com/ZayPhyo01-Droid/MovieModule/blob/main/screenshot/Screenshot%202024-06-26%20at%2012.10.24%20AM.png" />




## Features

- Home Screen
- Upcoming
- Detail 

## Network
Ktor Client


## Navigation

I used my own ksp processor to generate navigation route. You can check at processor module .


## Modules

core module for common logic module.

api module sit between core and feature module (as not to connect feature module each others).

feature for application feature module

annotations and processor module for ksp navigation processor 

baselineprofile module to generate baselineprofile for app startup (Will add dex layout optimization later)

