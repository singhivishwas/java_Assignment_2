import Assignment2.BlogPost;
import Assignment2.Person;
import org.junit.jupiter.api.Test;
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
