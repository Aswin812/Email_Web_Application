package com.emailApplication.dataLayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emailApplication.dto.AccountsDTO;
import com.emailApplication.dto.MailsDTO;

public class Repository {
	
	private static Repository repository;
	public static Repository getInstance() {
		if(repository==null)
			repository=new Repository();
		return repository;
	}
	
	public Map<String, AccountsDTO> accounts =new HashMap<>();
	public Map<String , List<MailsDTO>> inbox=new HashMap<>();
	public Map<String , List<MailsDTO>> sendMails=new HashMap<>();
	public Map<String , List<MailsDTO>> draftMails=new HashMap<>();
	public Map<String , List<MailsDTO>> favMails=new HashMap<>();
	public Map<String , List<MailsDTO>> trashMails=new HashMap<>();
	
	public void createAccount(String name, String email, String password, int age, String gender) {
		accounts.put(email, new AccountsDTO(name, password, age, gender));
	}
	
	public boolean emailVerification(String email, String password) {
		if(accounts.containsKey(email)&&accounts.get(email).getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	
	public boolean containsEmail(String email) {
		if(accounts.containsKey(email)) {
			return true;
		}
		return false;
	}
	
	public boolean sendMails(String from, String to, String sub, String content) {
		List<MailsDTO> send = sendMails.getOrDefault(from, new ArrayList<>());
		send.add(new MailsDTO(to, sub, content));
		sendMails.put(from, send);
		List<MailsDTO> list=inbox.getOrDefault(to, new ArrayList<>());
		list.add(new MailsDTO(from, sub, content));
		inbox.put(to, list);
		return true;
	}
	
	public void draftMails(String from, String to, String sub, String content) {
		List<MailsDTO> list = draftMails.getOrDefault(from, new ArrayList<>());
		list.add(new MailsDTO(to, sub, content));
		draftMails.put(from, list);
	}
	
	public void addToFav(String email, String to, String sub, String content) {
		List<MailsDTO> list=favMails.getOrDefault(email, new ArrayList<MailsDTO>());
		list.add(new MailsDTO(to, sub, content));
		favMails.put(email, list);
	}
	
	public void moveToTrash(String email, String to, String sub, String content) {
		List<MailsDTO> list=trashMails.getOrDefault(email, new ArrayList<MailsDTO>());
		list.add(new MailsDTO(to, sub, content));
		trashMails.put(email, list);
	}

	public void removeInboxMail(String email, String to, String sub, String content) {
		List<MailsDTO> inboxList=inbox.get(email);
		for(MailsDTO inb : inboxList) {
			if(inb.getToEmail().equals(to)&&inb.getSubject().equals(sub)&&inb.getContent().equals(content)) {
				inboxList.remove(inb);
				favMails.put(email, inboxList);
				return;
			}
		}
	}
	
	public void removeSendMail(String email, String to, String sub, String content) {
		List<MailsDTO> sendList=sendMails.get(email);
		for(MailsDTO send : sendList) {
			if(send.getToEmail().equals(to)&&send.getSubject().equals(sub)&&send.getContent().equals(content)) {
				sendList.remove(send);
				favMails.put(email, sendList);
				return;
			}
		}
	}
	
	public void removeDraftMail(String email, String to, String sub, String content) {
		List<MailsDTO> draftList=draftMails.get(email);
		for(MailsDTO draft : draftList) {
			if(draft.getToEmail().equals(to)&&draft.getSubject().equals(sub)&&draft.getContent().equals(content)) {
				draftList.remove(draft);
				favMails.put(email, draftList);
				return;
			}
		}
	}
	
	public void removeFav(String email, String to, String sub, String content) {
		List<MailsDTO> favList=favMails.get(email);
		for(MailsDTO fav : favList) {
			if(fav.getToEmail().equals(to)&&fav.getSubject().equals(sub)&&fav.getContent().equals(content)) {
				favList.remove(fav);
				favMails.put(email, favList);
				return;
			}
		}
	}
	
	public void removeTrashMail(String email, String to, String sub, String content) {
		List<MailsDTO> trashList=trashMails.get(email);
		for(MailsDTO trash : trashList) {
			if(trash.getToEmail().equals(to)&&trash.getSubject().equals(sub)&&trash.getContent().equals(content)) {
				trashList.remove(trash);
				favMails.put(email, trashList);
				break;
			}
		}
	}
	
	public List<MailsDTO> getInboxList(String email){
		if(inbox.containsKey(email)) {
			return inbox.get(email);
		}
		return new ArrayList<>();
	}
	
	public List<MailsDTO> getSentMailList(String email){
		if(sendMails.containsKey(email)) {
			return sendMails.get(email);
		}
		return new ArrayList<>();
	}
	
	public List<MailsDTO> getDraftMailList(String email){
		if(draftMails.containsKey(email)) {
			return draftMails.get(email);
		}
		return new ArrayList<>();
	}
	
	public List<MailsDTO> getFavList(String email){
		if(favMails.containsKey(email)) {
			return favMails.get(email);
		}
		return new ArrayList<>();
	}
	
	public List<MailsDTO> getDeletedList(String email){
		if(trashMails.containsKey(email)) {
			return trashMails.get(email);
		}
		return new ArrayList<>();
	}
	
	public boolean isFav(String email, String to, String sub, String content) {
		List<MailsDTO> favList=favMails.getOrDefault(email, new ArrayList<MailsDTO>());
		for(MailsDTO mails: favList) {
			if(mails.getToEmail().equals(to)&&mails.getSubject().equals(sub)&&mails.getContent().equals(content)) {
				return true;
			}
		}
		return false;
	}
}

