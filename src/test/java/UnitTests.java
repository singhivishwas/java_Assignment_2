import Assignment2.BlogPost;
import Assignment2.Person;
import Assignment2.Blog;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void testPersonCreationSuccess() {
        Person person = Person.builder()
                .id("1")
                .firstName("Vishwas")
                .lastName("Singhi")
                .age(24)
                .gender("Male")
                .build();
        assertNotNull(person);
    }

    @Test
    void testPersonCreationFailsIfIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person.builder()
                    .id(null)
                    .firstName("Vishwas")
                    .lastName("Singhi")
                    .age(24)
                    .gender("Male")
                    .build();
        });
        assertEquals("ID cannot be null.", exception.getMessage());
    }
}

class BlogPostTest {
    @Test
    void testBlogPostCreationSuccess() {
        BlogPost blogPost = BlogPost.builder()
                .id("2426")
                .authorId("7")
                .postContent("Welcome to my blog post guys.")
                .build();
        assertNotNull(blogPost);
    }

    @Test
    void testBlogPostCreationFailsIfAuthorIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BlogPost.builder()
                    .id("2426")
                    .authorId(null)
                    .postContent("Another blog post.")
                    .build();
        });
        assertEquals("Author ID cannot be null.", exception.getMessage());
    }
}

class BlogTest {

    @Test
    public void testGetPostsByAuthorAge() {
        // Create mock data
        Person vishwas = new Person("p1", "Vishwas", "Singhi", 24, "Male");
        Person lakshay = new Person("p2", "Lakshay", "Girdher", 20, "Male");
        BlogPost post1 = new BlogPost("b1", "p1", "Hello world!");
        BlogPost post2 = new BlogPost("b2", "p2", "Java streams are awesome!");

        List<Person> contributors = Arrays.asList(vishwas, lakshay);
        List<BlogPost> posts = Arrays.asList(post1, post2);

        Blog blog = new Blog(posts, contributors);

        // Test getPostsByAuthorAge
        List<String> result = blog.getPostsByAuthorAge(24);
        assertEquals(1, result.size());
        assertEquals("b1", result.get(0));

        // Test with no matches
        List<String> noMatch = blog.getPostsByAuthorAge(40);
        assertEquals(0, noMatch.size());
    }
    @Test
    public void testEmptyInput() {
        // Test with empty blog posts and contributors
        Blog blog = new Blog(Collections.emptyList(), Collections.emptyList());

        // No blog posts should match any age
        List<String> result = blog.getPostsByAuthorAge(30);
        assertEquals(0, result.size());
    }

    @Test
    public void testMissingAuthorIds() {
        // Create mock data with a post that has no matching author
        Person vishwas = new Person("p1", "Vishwas", "Singhi", 24, "Male");
        BlogPost post1 = new BlogPost("b1", "p1", "Welcome to my new blog guys...");
        BlogPost post2 = new BlogPost("b2", "p3", "Author ID not found!"); // Missing author ID

        List<Person> contributors = Collections.singletonList(vishwas);
        List<BlogPost> posts = Arrays.asList(post1, post2);

        Blog blog = new Blog(posts, contributors);

        // Post b2 should not be returned as it has no matching author
        List<String> result = blog.getPostsByAuthorAge(24);
        assertEquals(1, result.size());
        assertEquals("b1", result.get(0));
    }

    @Test
    public void testInvalidData() {
        // Create mock data with invalid age
        assertThrows(IllegalArgumentException.class, () -> new Person("p1", "Vishwas", "Singhi", -1, "Male")); // Negative age

        // Create mock data with null ID
        assertThrows(IllegalArgumentException.class, () -> new BlogPost(null, "p1", "Invalid post with null ID"));

        // Create mock data with null author ID
        assertThrows(IllegalArgumentException.class, () -> new BlogPost("b1", null, "Invalid post with null author ID"));
    }
}
