# **FOOD_DECIDER**

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)

## App Overview

### Description 

**Helps users decide what to eat by providing personalized recommendations based on their preferences and location. For dining out, it considers price range, cuisine preferences, and dietary restrictions. For cooking at home, it factors in diet choices, cuisine preferences, and available ingredients.**

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:** Food and Drinks / Lifestyle
- **Mobile:** Mobile is essential for this app's core functionality. Location services are critical for finding nearby restaurants, and the camera could be used to scan ingredients or menus. The on-the-go nature of food decisions makes mobile the ideal platform. Push notifications could remind users about meal times or suggest recipes based on ingredients nearing expiration.
- **Story:** Resonates well with a common daily struggle to decide among food choices. It positions itself as a personal food assistant that eliminates the "what should I eat?" dilemma. The narrative of personalized recommendations that learn from your preferences and adapt to your situation (location, budget, dietary needs) is compelling and relatable.
- **Market:** The market is substantial and diverse. Target users include busy professionals, college students, people with dietary restrictions, cooking enthusiasts, and anyone who experiences decision fatigue around food. Revenue opportunities include restaurant partnerships, premium features for meal planning, ingredient delivery service integrations, and sponsored recipe content. The food app market is proven and growing.
- **Habit:** Usage frequency depends on implementation. People make food decisions 3+ times daily, creating natural usage opportunities. However, forming a habit requires the app to be significantly better than alternatives (Google Maps, Yelp, recipe apps). Success depends on personalization accuracy and convenience. Without strong habit formation, users might revert to familiar apps or asking friends for recommendations.
- **Scope:** 
    - V1: Basic recommendation engine for restaurants with filters (price, cuisine, distance) and simple recipe suggestions based on available ingredients. Could be tested with a small user group.
    - V2: Add user preference learning, ratings/reviews integration, and dietary restriction handling.
    - V3: Incorporate meal planning features, shopping list generation, and ingredient scanning.
    - V4: Add social features (sharing recommendations), restaurant reservation integration, and grocery delivery partnerships.

## Product Spec

### 1. User Features (Required and Optional)

Required Features:
<!-- V1: Basic recommendation engine for restaurants with filters (price, cuisine, distance) and simple recipe suggestions based on available ingredients. Could be tested with a small user group. -->

- **Choose to eat out or cook at home**
- **Dining out: search by food**
- **Dining out: pick restaurant**
- **Cooking: recipe search by one main ingredient**

Stretch Features:

- **Dining out: filter by price, location, distance**
- **Cooking: search for recipes by main ingredients**

### 2. Chosen API(s)

- **https://business.yelp.com/data/products/fusion/**
  - **User inputs their food choice, such as pizza**
- **www.themealdb.com/api/json/v1/1/filter.php?i=chicken**
  - **User tick a box of one main ingredient, such as chicken**

### 3. User Interaction

Required Feature
- **App will randomly decide which restaurant for user to choose**
- **Choose to eat out or cook at home**
  - => **Eating out: Stay in the same page. Cooking: Go to ingredient search page**
  - **Eating out: Type in a food option**
      - => **Apps gives 3 restaurants serving that food**
  - **Cooking: Choose a main ingredient**
      - => **Apps gives multiple recipe suggestions for that ingredient**

## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->
<img src="https://i.imgur.com/W2BroAc.jpeg" width=600>
<img src="https://i.imgur.com/Xq61BPz.jpeg" width=600>
<img src="https://i.imgur.com/Jnp43Nv.jpeg" width=600>

### [BONUS] Digital Wireframes & Mockups
<img src="https://i.imgur.com/ks5qbpF.png">

### [BONUS] Interactive Prototype
<img src="https://i.imgur.com/rmEcs8n.gif" width=600>

## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!
<img src="https://i.imgur.com/u7GLae2.gif" width=600>
<img src="https://i.imgur.com/Qd8TWbq.gif" width=600>

## License

Copyright **2025** **Team 23: Kiara Guerra, Trang Tran, Michael Sim, Favour Asomba**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
