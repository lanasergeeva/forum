package ru.job4j.forum.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Reply;
import ru.job4j.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMemory {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger postIndex = new AtomicInteger(1);
    private final AtomicInteger replyIndex = new AtomicInteger(1);

    public PostMemory() {
        User user = User.of("lana", "123", Authority.of("admin"));
        Post post1 = Post.of("Продаю машину ладу 01.", "Данная стоимость автомобиля является информационной. "
                + "Возможно приобретение автомобиля по программе «Трейд-ин»* выгода до 80 000 руб., "
                + "и/или при условии добровольного оформления КАСКО* выгода до 30 000 руб. "
                + "До подписания договора-купли продажи ДЦ Hyundai ООО «Сэнд-Авто» вправе "
                + "изменить комплектацию, стоимость, условия продажи указанного автомобиля в "
                + "одностороннем порядке. *Выгода при сдаче автомобиля по программе \"Tрейд-ин\" "
                + "учитывается после окончательной стоимости автомобиля с учетом дополнительного "
                + "оборудования и иных опций. **Компании - партнеры: СК \"Гайдэ\" Лицензия ЦБ РФ "
                + "№ 0630 ОТ 26.01.2017 , СК \"ТИТ\" Лицензия № 1182 от 15.06.2017 г . "
                + "Для получения подробной информации о наличии, окончательной стоимости автомобиля, "
                + "условий кредитования/страхования, сдачи автомобиля по программе \"Трейд-ин\" и "
                + "специальных программ дилерского центра - обращайтесь в отдел продаж ДЦ Hyundai Сэнд-Авто.", user);
        Post post2 = Post.of("Какая-то тема.", "Какое-то второе описание", user);
        Post post3 = Post.of("Новая тема.", "Какое-то описание", user);
        Post post4 = Post.of("Куплю холодильник.", "Какое-то описание", user);
        save(post1);
        save(post2);
        save(post3);
        save(post4);
    }

    public List<Post> findAll() {
        System.out.println(posts);
        return new ArrayList<>(posts.values());
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(postIndex.getAndIncrement());
            post.setCreated(Calendar.getInstance());
        } else {
            Post post1 = posts.get(post.getId());
            post.setCreated(post1.getCreated());
            post.setReplies(post1.getReplies());
        }
        posts.put(post.getId(), post);
    }

    public void delete(int id) {
        Post post = posts.get(id);
        if (post != null) {
            posts.remove(id, post);
        }
    }

    public void addReplyMem(Post post, Reply reply) {
        if (reply.getId() == 0) {
            reply.setId(replyIndex.getAndIncrement());
        }
        post.addReply(reply);
    }

    public void deleteReplyMem(int postId, int replyId) {
        Post post = posts.get(postId);
        List<Reply> replies = post.getReplies();
        replies.removeIf(reply -> reply.getId() == replyId);
    }
}
