package com.example.assignmentthree;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        String bookTitle = extras.getString("bookTitle");

        int imageResource = 0;
        String title = "";
        String description = "";
        String author = "";
        String price = "";

        switch (bookTitle) {
            case "Nordic Islands: Iceland, Greenland, Norway, Faroe Islands":
                imageResource = R.drawable.nordic;
                title = "Nordic Islands: Iceland, Greenland, Norway, Faroe Islands";
                description = "One of the most renowned nature photographers in Europe, Stefan Forster, documents the extraordinary Nordic islands. Over the course of more than 80 intrepid trips through Greenland, Iceland, Norway, and the Faroe Islands, Forster captures the epic landscapes, wildlife, and beauty of these far-flung isles. Forster?s journey saw him walking hundreds of kilometers through the Highlands of Iceland, paddling a kayak along the west coast of Greenland, sailing on an old wooden ship through the mighty Scoresby Sound of East Greenland, and scaling one of the steepest cliffs on the Faroe Islands. His photographs capture not only these extraordinary landscapes, but also their native inhabitants: young polar foxes, musk ox families, sea eagles, and puffins. The result is a fascinating photographic portrait of some of the most remote and ruggedly beautiful places on earth.";
                author = "Forster Stefan";
                price = "$103.40";
                break;
            case "Elements: In Pursuit of the Wild":
                imageResource = R.drawable.elements;
                title = "Elements: In Pursuit of the Wild";
                description = "Elements In Pursuit of the Wild, is a powerful and moving visual journey of discovery created by the editors of Rucksack Magazine. In this compilation are stories, interviews, and stunning photographs that highlight locations where we are overwhelmed by the beauty of nature. These wild places embody peace and tranquility, and exploring them requires courage, a sense of adventure, and an intrepid curiosity about the world. Locations featured in this book include the Faroe Islands, the northwest Pacific, Scandinavia, and Scotland, among other places. ";
                author = "Ron Brown";
                price = "$65.00";
                break;
            case "Seeing Silence: The Beauty of the World’s Most Quiet Places":
                imageResource = R.drawable.silence;
                title = "Seeing Silence: The Beauty of the World’s Most Quiet Places";
                description = "Award-winning photographer Pete McBride takes readers on a remarkable journey to discover places of peace and quiet in a congested and noisy world. In his book, McBride showcases stunning imagery of serene locations, from Mount Everest to the Grand Canyon, the Atacama Desert to the African savannah, and the Antarctic Peninsula to the Ganges and Nile rivers. These breathtaking photographs capture the magic of being \"truly away\" and serve as a reminder of the disappearing tranquility in our world. McBride's work celebrates the power of nature's sounds and offers both inspiration and a sense of calm to its readers.";
                author = "Pete McBride";
                price = "$29.32";
                break;
            case "Floating Luxury: The Most Luxurious Cruise Ships":
                imageResource = R.drawable.floating;
                title = "Floating Luxury: The Most Luxurious Cruise Ships";
                description = "Cruise ships offer an intriguing escape. These incredible ships have always captured the imagination, they are unique, luxurious and have increasingly become floating cities on the oceans of the world thanks to innovative engineering and design. They are opulent hotels able to accommodate over a thousand people in comfort, with the opportunity for fine dining, entertainment, a range of leisure and sporting activities along with a slice of retail therapy.";
                author = "Iwein Maassen";
                price = "$84.24";
                break;
            case "Mi Cocina: Recipes and Rapture from My Kitchen in Mexico":
                imageResource = R.drawable.cocina;
                title = "Mi Cocina: Recipes and Rapture from My Kitchen in Mexico";
                description = "Join Rick Martínez on a once-in-a-lifetime culinary journey throughout México that begins in Mexico City and continues through 32 states, in 156 cities, and across 20,000 incredibly delicious miles. In Mi Cocina, Rick shares deeply personal recipes as he re-creates the dishes and specialties he tasted throughout his journey. Inspired by his travels, the recipes are based on his taste memories and experiences. True to his spirit and reflective of his deep connections with people and places, these dishes will revitalize your pantry and transform your cooking repertoire.";
                author = "Rick Martínez";
                price = "$37.30";
                break;
            case "Mandy's Gourmet Salads: Recipes for Lettuce and Life":
                imageResource = R.drawable.mandy;
                title = "Mandy's Gourmet Salads: Recipes for Lettuce and Life";
                description = "In Mandy's Gourmet Salads, Mandy and Rebecca talk you through how to create their coveted salads at home, including easy prep steps for essential ingredients, how to mix their famous dressings, and how to combine flavours and textures to create a salad masterpiece. ";
                author = "Mandy Wolfe";
                price = "$35.00";
                break;
            case "Korean American: Food That Tastes Like Home":
                imageResource = R.drawable.koreanamerican;
                title = "Korean American: Food That Tastes Like Home";
                description = "New York Times staff writer Eric Kim's debut cookbook, \"Korean American,\" combines Korean flavors with American classics. Through personal stories and recipes like Gochujang-Buttered Radish Toast and Cheeseburger Kimbap, Eric celebrates his Korean heritage while bridging culinary traditions. The book explores the Korean pantry, the history of Korean cooking in America, and the transformative power of food in finding acceptance and strength. With stunning visuals and indulgent treats like Gochujang Chocolate Lava Cakes, \"Korean American\" is a delightful exploration of identity and delicious flavors.";
                author = "Eric Kim";
                price = "$30.07";
                break;
            case "Favorite Cakes: Showstopping Recipes for Every Occasion":
                imageResource = R.drawable.cakes;
                title = "Favorite Cakes: Showstopping Recipes for Every Occasion";
                description = "\"Favorite Cakes\" is a delightful collection of cakes for every occasion. From simple tea cakes to extravagant multi-tiered creations, this book offers 40 recipes with easy-to-follow directions and decorating tips. Whether you're craving a fruity cake for afternoon tea or a show-stopping dessert for a special event, you'll find inspiration here. Discover reinvented classics like Champagne and Raspberry Mini Layer Cake and indulge in decadent treats like Dulce de Leche Crepe Cake. With stunning photography and essential baking techniques, this book is a must-have for cake lovers. Developed by the Williams Sonoma Test Kitchen, it's a perfect gift for any baking enthusiast.";
                author = "Williams Sonoma Test Kitchen";
                price = "$24.95";
                break;
            case "Java Coding Guidelines: 75 Recommendations for Reliable and Secure Programs":
                imageResource = R.drawable.coding;
                title = "Java Coding Guidelines: 75 Recommendations for Reliable and Secure Programs";
                description = "Java™ Coding Guidelines is a must-read for Java developers, providing expert guidance on writing secure, reliable, and maintainable code. This book, endorsed by industry professionals, offers 75 guidelines with conformance requirements, code examples, and solutions. It covers Java security, best practices for code reliability, and addresses common misunderstandings. With a foreword by James A. Gosling, this comprehensive resource is essential for meeting the demands of mission-critical Java applications.";
                author = "Fred Long";
                price = "$48.58";
                break;
            case "Why Things Bite Back: Technology and the Revenge of Unintended Consequences":
                imageResource = R.drawable.bites;
                title = "Why Things Bite Back: Technology and the Revenge of Unintended Consequences";
                description = "In this perceptive and provocative look at everything from computer software that requires faster processors and more support staff to antibiotics that breed resistant strains of bacteria, Edward Tenner offers a virtual encyclopedia of what he calls \"revenge effects\"--the unintended consequences of the mechanical, chemical, biological, and medical forms of ingenuity that have been hallmarks of the progressive, improvement-obsessed modern age. Tenner shows why our confidence in technological solutions may be misplaced, and explores ways in which we can better survive in a world where despite technology's advances--and often because of them--\"reality is always gaining on us.";
                author = "Edward Tenner";
                price = "$22.50";
                break;
            case "The Distracted Mind: Ancient Brains in a High-Tech World":
                imageResource = R.drawable.distractedmind;
                title = "The Distracted Mind: Ancient Brains in a High-Tech World";
                description = "Why our brains aren't built for media multitasking, and how we can learn to live with technology in a more balanced way. The Distracted Mind by Adam Gazzaley is a highly engaging read of how we cognitively pursue our goals and how our brains have to frequently overcome internal and external interference, including that from modern technologies, to do this successfully. The book includes excellent, clear examples of what these problems of goal interference are and how they might affect us in our daily lives. It is a very informative and extremely interesting read, which is strongly recommended for all those with an interest in neuroscience, psychology, and the impact of technology on society.";
                author = "Adam Gazzaley";
                price = "$23.95";
                break;
            case "Ethics in Technology: A Philosophical Study":
                imageResource = R.drawable.ethics;
                title = "Ethics in Technology: A Philosophical Study";
                description = "This book explores ethics in a technological world, addressing challenges, traditional limitations, and the impact of scientific progress. It examines key approaches and emphasizes the importance of philosophy of technology in shaping ethical considerations. The author advocates for interdisciplinary dialogue and highlights the significance of \"mid-level ethics\" between individual choices and societal principles.";
                author = "Topi Heikkerö";
                price = "$64.99";
                break;
            case "Good Night, Little Blue Truck":
                imageResource = R.drawable.littlebluetruck;
                title = "Good Night, Little Blue Truck";
                description = "Beep! Beep! Beep! It's time for sleep,\" announces the Little Blue Truck in this delightful bedtime story. As a storm approaches, Little Blue Truck and his friend Toad hurry home, but the noise of thunder and lightning makes it hard to sleep. Soon, other friends seek shelter from the storm, and together they find comfort and bravery in each other's company. When the storm subsides, they embark on a peaceful bedtime ride. With its sweet and humorous tone, this book is a perfect bedtime read for the millions of Little Blue Truck fans.";
                author = "Alice Schertle ";
                price = "$16.51";
                break;
            case "The Wonderful Things You Will Be":
                imageResource = R.drawable.wonderful;
                title = "The Wonderful Things You Will Be";
                description = "From brave and bold to creative and clever, Emily Winfield Martin's rhythmic rhyme expresses all the loving things that parents think of when they look at their children. With beautiful, and sometimes humorous, illustrations, and a clever gatefold with kids in costumes, this is a book grown-ups will love reading over and over to kids—both young and old. A great gift for any occasion, but a special stand-out for baby showers, birthdays, and graduation. The Wonderful Things You Will Be has a loving and truthful message that will endure for lifetimes.";
                author = "Emily Winfield Martin";
                price = "$21.00";
                break;
            case "The Very Hungry Caterpillar":
                imageResource = R.drawable.caterpillar;
                title = "The Very Hungry Caterpillar";
                description = "\"The Very Hungry Caterpillar\" is an all-time classic picture book that has been cherished by generations worldwide, with a copy sold every 30 seconds somewhere in the world. Have you shared this beloved story with a child or grandchild in your life? This beautiful board book edition features a special interactive feature with die cuts, making it perfect for teaching the days of the week. As the very hungry caterpillar munches its way through the pages, it captures not only the imagination of children but also their hearts. With stunning illustrations and innovative storytelling, this book has received high praise, including being called \"gorgeously illustrated\" and \"brilliantly innovative\" by The New York Times Book Review.";
                author = "Eric Carle";
                price = "$11.88";
                break;
            case "The Very Sleepy Bear":
                imageResource = R.drawable.bear;
                title = "The Very Sleepy Bear";
                description = "The story follows a big and brave bear who is looking for a new home. With the suggestion from a sneaky fox, they embark on a journey to explore different places. From a noisy train tunnel to a hollow tree with bats and even an oceanfront option with great views but dampness, Bear tries out various locations. However, in the end, Bear realizes that perhaps their own cozy cave is the perfect home after all. This heartwarming tale teaches the value of appreciating what we already have and finding comfort in familiar surroundings. It is a delightful story to share with young readers before bedtime, reminding them of the importance of feeling safe and content in their own space.";
                author = "Nick Bland";
                price = "$9.99";
                break;
        }

        ImageView bookImage = findViewById(R.id.bookImageView);
        bookImage.setImageResource(imageResource);

        TextView bookTitleTextView = findViewById(R.id.titleTextView);
        bookTitleTextView.setText(title);

        TextView bookDescriptionTextView = findViewById(R.id.descriptionTextView);
        bookDescriptionTextView.setText(description);

        TextView bookAuthorTextView = findViewById(R.id.authorTextView);
        bookAuthorTextView.setText(author);

        TextView bookPriceTextView = findViewById(R.id.priceTextView);
        bookPriceTextView.setText(price);

        Button addToCartButton = findViewById(R.id.addToCartButton);
        // Set onClickListener for the addToCartButton
        addToCartButton.setOnClickListener(v -> {
            // Code to handle "add to cart" click
        });
    }
}
