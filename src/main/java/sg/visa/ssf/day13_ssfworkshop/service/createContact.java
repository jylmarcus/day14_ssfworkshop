package sg.visa.ssf.day13_ssfworkshop.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import sg.visa.ssf.day13_ssfworkshop.model.Contacts;

@Service
public class createContact {

    public void create(Contacts contact, String dataDir, Model model) {
        String fileName = contact.getId();
        try{
            FileWriter fw = new FileWriter(dataDir + "/" + fileName + ".txt");
            PrintWriter pw = new PrintWriter(fw);
            
            pw.println(contact.getName());
            pw.println(contact.getEmail());
            pw.println(contact.getTel());
            pw.println(contact.getDob());
            model.addAttribute("contact", new Contacts(contact.getId(), contact.getName(), contact.getEmail(), contact.getTel(),contact.getDob())); 

            pw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public List<Contacts> getContacts(String dataDir) {
        List<Contacts> contactList = new ArrayList<Contacts>();
        File dataDirFile = new File(dataDir);
        for(File contactFile : dataDirFile.listFiles()) {
            try{
                FileReader fr = new FileReader(contactFile);
                BufferedReader br = new BufferedReader(fr);
                String name = br.readLine();
                String email = br.readLine();
                String tel = br.readLine();
                LocalDate dob = LocalDate.parse(br.readLine());
                
                String id = contactFile.getName().substring(0, contactFile.getName().length() - 4).toUpperCase();
                Contacts contact = new Contacts(id, name, email, tel, dob);
                contactList.add(contact);

                br.close();
                fr.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return contactList;
    }
}
