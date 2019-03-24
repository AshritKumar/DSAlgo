package practice.algo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Friend {
	private Collection<Friend> friends;
	private String email;

	public Friend(String email) {
		this.email = email;
		this.friends = new ArrayList<Friend>();
	}

	public String getEmail() {
		return email;
	}

	public Collection<Friend> getFriends() {
		return friends;
	}

	public void addFriendship(Friend friend) {
		friends.add(friend);
		friend.getFriends().add(this);
	}

	public boolean canBeConnected(Friend friend) {
		Set<String> visitedFrnds = new HashSet<>();
		LinkedList<Friend> frndList = new LinkedList<>();
		frndList.add(this);
		visitedFrnds.add(this.email);
		while(! frndList.isEmpty()) {
			Friend f = frndList.poll();
			List<Friend> adjFrns = (List<Friend>)f.getFriends();
			for(Friend f1 : adjFrns) {
				if(f1.getEmail().equals(friend.getEmail()))
					return true;
				if(! visitedFrnds.contains(f1.getEmail())) {
					visitedFrnds.add(f1.getEmail());
					frndList.add(f1);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Friend a = new Friend("A");
		Friend b = new Friend("B");
		Friend c = new Friend("C");
		Friend d = new Friend("D");

		a.addFriendship(b);
		b.addFriendship(c);

		System.out.println(a.canBeConnected(d));
	}
}