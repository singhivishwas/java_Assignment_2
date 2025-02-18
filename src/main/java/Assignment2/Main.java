// Main.java
package Assignment2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // Read JSON files into lists
            List<Person> persons = mapper.readValue(new File("person.json"), new TypeReference<List<Person>>() {});
            List<BlogPost> blogPosts = mapper.readValue(new File("blogPosts.json"), new TypeReference<List<BlogPost>>() {});

            // Validate: Check if all authorIds in blogPosts exist in persons
            Set<String> validAuthorIds = persons.stream()
                    .map(Person::getId)
                    .collect(Collectors.toSet());
            List<BlogPost> validBlogPosts = blogPosts.stream()
                    .filter(post -> {
                        if (!validAuthorIds.contains(post.getAuthorId())) {
                            System.err.println("Warning: BlogPost with ID " + post.getId()
                                    + " has an invalid authorId: " + post.getAuthorId());
                            return false; // Exclude invalid blog posts
                        }
                        return true; // Include valid blog posts
                    })
                    .toList();

            // Create a Blog instance
            Blog blog = new Blog(blogPosts, persons);

            // Example: Get posts by authors aged 24
            Integer age = 24;
            List<String> postsByAge = blog.getPostsByAuthorAge(age);
            System.out.println("Posts by authors aged " + age + ": " + postsByAge);

            // Print the total number of blog posts and contributors
            System.out.println("Total blog posts: " + blogPosts.size());
            System.out.println("Total contributors: " + persons.size());

        } catch (IOException e) {
            // Handle cases where files are missing or corrupted
            System.err.println("Error reading JSON files: " + e.getMessage());
        }
    }
}