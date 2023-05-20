package sg.visa.ssf.day13_ssfworkshop.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import sg.visa.ssf.day13_ssfworkshop.model.Contacts;

@Repository
public class ContactsRedis {
    @Autowired
    private RedisTemplate<String, Object> template;

    public void create(Contacts contact, Model model) {
        template.opsForHash().put("contactList", contact.getId(), contact);
        model.addAttribute("contact", contact);
    }

    public Optional<Object> getContact(String id) {
        return Optional.ofNullable(template.opsForHash().get("contactList", id));
    }

    public List<Contacts> getAllContacts(Model model) {
        return template.opsForHash().values("contactList").stream().filter(Contacts.class :: isInstance).map(Contacts.class :: cast).collect(Collectors.toList());
    }
}
