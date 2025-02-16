import Assignment2.BlogPost;
import Assignment2.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void testPersonCreationSuccess() {
        Person person = Person.builder()
                .id("1")
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .gender("Male")
                .build();
        assertNotNull(person);
    }

    @Test
    void testPersonCreationFailsIfIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Person.builder()
                    .id(null)
                    .firstName("John")
                    .lastName("Doe")
                    .age(30)
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
                .id("101")
                .authorId("1")
                .postContent("This is a blog post.")
                .build();
        assertNotNull(blogPost);
    }

    @Test
    void testBlogPostCreationFailsIfIdIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            BlogPost.builder()
                    .id(null)
                    .authorId("1")
                    .postContent("This is a blog post.")
                    .build();
        });
        assertEquals("ID cannot be null.", exception.getMessage());
    }
}
