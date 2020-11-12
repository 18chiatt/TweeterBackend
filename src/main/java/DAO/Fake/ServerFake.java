package DAO.Fake;

import model.Response.*;
import model.domain.Follow;
import model.domain.Status;
import model.domain.User;
import model.request.*;

import java.util.*;

public class ServerFake {
    private final int numPeopleToGenerate = 200;
    private final int numPeopleEveryoneFollows = 20;
    private final int numTweets = 10;
    private SortedSet<Status> theTweetsLocal;
    private List<User> theUsersLocal;
    private List<Follow> theFollowsLocal;







    public ServerFake() {
        super();


    }


    public FollowingResponse getFollowing(FollowingRequest req) {

        User personWhoFollows = req.getPersonWhoFollows();
        int max = req.getLimit();
        return getUsersBeingFollowed(personWhoFollows,max,req.getLastOneGotten());



    }


    public FollowerResponse getFollowers(FollowerRequest req) {
        User toFindOf = req.getWhoTheyFollow();
        int max = req.getMaxToGet();
        FollowerResponse resp = getThoseFollowing(toFindOf,req.getMaxToGet(),req.getPreviousLast());


        return resp;

    }






    public LoginResponse login(LoginRequest req) {


        User hardCodedResponse = new User("Chase","Hiatt","username","https://pbs.twimg.com/profile_images/1305883698471018496/_4BfrCaP_400x400.jpg");

        return new LoginResponse(hardCodedResponse,"1234",true);
    }


    public UserStatsResponse getUserStats(UserStatsRequest req) {
        int followers = 0;
        int peopleFollowing = 0;
        User toFindOf = req.getToFindOf();

        for(Follow follow : theFollowsLocal){

            if(follow.getFollower().equals(toFindOf)){
                peopleFollowing++;
            }
            if(follow.getPersonBeingFollowed().equals(toFindOf)){
                followers++;
            }

        }
        return new UserStatsResponse(followers,peopleFollowing);

    }


    public StoryResponse getStory(StoryRequest req) {
        List<Status> toReturn = new ArrayList<>();
        Iterator<Status> iter = theTweetsLocal.iterator();

        if(req.getPreviousLast() != null){
            while(iter.hasNext() ){
                if(iter.next().equals(req.getPreviousLast())){
                    break;
                }
            }

        }

        int remaining = req.getMaxToGet();
        while(iter.hasNext() && remaining > 0){
            Status next = iter.next();
            if(next.getSaidBy().equals(req.getToGetOf())){
                remaining--;
                toReturn.add(next);
            }
        }

        StoryResponse actualToReturn = new StoryResponse(toReturn,remaining==0);

        return actualToReturn;
    }





    private FollowerResponse getThoseFollowing(User toFindOf, int maxToGet, User lastToNotInclude) {
        int index =0;
        boolean hasMore;

        Deque<User> toReturn = new ArrayDeque<>();
        if(lastToNotInclude != null){
            while(!theFollowsLocal.get(index).getFollower().equals(lastToNotInclude) || theFollowsLocal.get(index).getPersonBeingFollowed().equals(toFindOf) == false){ //while follow[index] is NOT lastToNotInclude, iterate
                index++;
            }
            //index is lastToNotInclude
            index++; //index is now starting index
        }

        int remaining = maxToGet;
        for(int i = index; i < theFollowsLocal.size() && remaining > 0; i ++){
            if(theFollowsLocal.get(i).getPersonBeingFollowed().equals(toFindOf)){
                toReturn.add(theFollowsLocal.get(i).getFollower());
                remaining--;
            }
        }


        hasMore = (remaining == 0);
        if(toReturn.size() != 0) {
            if (lastToNotInclude != null && toReturn.getFirst().equals(lastToNotInclude)) {
                toReturn.pop();
            }
        }
        List<User> listToReturn = new ArrayList<>();
        listToReturn.addAll(toReturn);
        return new FollowerResponse(listToReturn, hasMore);






    }

    private FollowingResponse getUsersBeingFollowed(User toFindOf,int maxToGet, User lastToNotInclude){

        int index = 0;
        List<User> toReturn = new ArrayList<>();
        if(lastToNotInclude != null){

            while(theFollowsLocal.get(index).getPersonBeingFollowed().equals(lastToNotInclude) == false || theFollowsLocal.get(index).getFollower().equals(toFindOf) == false){ //while follow[index] is NOT lastToNotInclude, iterate
                index++;
            }
            //index is lastToNotInclude
            index++; //index is now starting index
        }


        int remaining = maxToGet;
        for(int i = index; i < theFollowsLocal.size() && remaining > 0; i++){
            if(theFollowsLocal.get(i).getFollower().equals(toFindOf)){

                toReturn.add(theFollowsLocal.get(i).getPersonBeingFollowed());
                remaining--;
            }
        }

        boolean hasMore = false;
        if(remaining == 0){
            hasMore = true;
        }

        FollowingResponse resp = new FollowingResponse(toReturn,hasMore);


        return resp;

    }


    public UserResponse getUser(UserRequest req) {
        System.out.println(req.getAlias() + " is thing to find LOL");

        for(User u : theUsersLocal){
            if(u.getUserName().equals(req.getAlias())){

                return new UserResponse(u,true);
            }
        }
        System.out.println("Unable to find correct user!");
        System.out.println("This has " + theUsersLocal.size() +  " users, and " + theFollowsLocal.size()+ " Follows!");
        for ( User u : theUsersLocal){
            System.out.println(u.toString());
        }
        return new UserResponse(null,false);
    }


    public FeedResponse getFeed(FeedRequest req) {
        System.out.println(theTweetsLocal.size());
        System.out.println(theFollowsLocal.size());
        System.out.println(theUsersLocal.size());
        User toGetOf = req.getToGetFeedOf();
        Set<User> peopleTheyFollow = new HashSet<>();
        peopleTheyFollow.addAll( getUsersBeingFollowed(toGetOf,Integer.MAX_VALUE,null).getUsersTheyAreFollowing());
        System.out.println(peopleTheyFollow.size());

        List<Status> toReturn = new ArrayList<>();

        Iterator<Status> iter = theTweetsLocal.iterator();

        if(req.getPreviousLast() != null){
            while(iter.hasNext()){
                Status theStatus = iter.next();
                if(theStatus.equals(req.getPreviousLast())){
                    break;
                }
            }
        }

        int remaining = req.getMaxToGet();
        while(iter.hasNext() && remaining > 0){
            Status next = iter.next();
            if(peopleTheyFollow.contains(next.getSaidBy())){
                remaining--;
                toReturn.add(next);
            }

        }
        System.out.println(toReturn.size());

        return new FeedResponse(toReturn,remaining==0);

    }


    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest req) {
        for(Follow f : theFollowsLocal){
            if(f.getPersonBeingFollowed().equals(req.getPersonWhoIsFollowedMaybe()) && f.getFollower().equals(req.getPersonWhoFollowsMaybe())){
                return new FollowingStatusResponse(true);
            }
        }
        return new FollowingStatusResponse(false);
    }


    public FollowManipulationResult manipulateFollow(FollowManipulationRequest req) {
        if(req.isAddFollow()){
            if(req.getPersonWhoFollows().equals(req.getPersonWhoIsFollowed())){
                return new FollowManipulationResult(false,false);
            }
            theFollowsLocal.add(new Follow(req.getPersonWhoFollows(),req.getPersonWhoIsFollowed()));

            return new FollowManipulationResult(true,true);
        } else {
            for(Follow f : theFollowsLocal){
                if(f.getFollower().equals(req.getPersonWhoFollows()) && f.getPersonBeingFollowed().equals(req.getPersonWhoIsFollowed())){
                     //pretend to delete
                }
            }

            return new FollowManipulationResult(false,true);
        }
    }


    public PostStatusResponse postStatus(PostStatusRequest req) {
        theTweetsLocal.add(req.getTheStatus());
        return new PostStatusResponse(true);
    }


    public RegisterResponse registerUser(RegisterRequest req) {

        return new RegisterResponse(true);
    }






    public List<User>getAll(){
        return theUsersLocal;
    }
}