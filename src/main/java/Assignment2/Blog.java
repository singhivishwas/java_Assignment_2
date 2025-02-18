package Assignment2;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class Blog {
    private final List<BlogPost> posts;
    private final List<Person> contributors;

    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.contributors = contributors;
    }

    public List<String> getPostsByAuthorAge(Integer age) {
        return posts.stream()
                .filter(post -> {
                    // Find the contributor (author) with the matching authorId
                    Person author = contributors.stream()
                            .filter(person -> person.getId().equals(post.getAuthorId()))
                            .findFirst()
                            .orElse(null);
                    return author != null && author.getAge().equals(age); // Match age
                })
                .map(BlogPost::getId) // Get the BlogPost IDs
                .collect(Collectors.toList());
    }
}

