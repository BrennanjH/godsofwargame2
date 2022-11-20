package com.simplesoftwaresolutions.godsofwargame.messages.ingress.lobbymessages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.game.LoadState;
import com.simplesoftwaresolutions.godsofwargame.messages.Command;
import com.simplesoftwaresolutions.godsofwargame.messages.NullExpectedField;
import com.simplesoftwaresolutions.godsofwargame.player.Coalition;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.player.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "className")
public class ChangeCoalitionCommand implements Command {

    private static final Logger logger= LoggerFactory.getLogger(ChangeCoalitionCommand.class);

    //The name of the team that session is switching too
    String switchTo;

    @JsonCreator
    public ChangeCoalitionCommand(@JsonProperty("teamName") String newTeamName){
        switchTo = newTeamName;
    }

    @Override
    public void execute(GameState gameState, WebSocketSession session){
        try {
            if(switchTo == null) {
                throw new NullExpectedField("switchTO is null");
            }
            if( gameState.loadState == LoadState.LOBBY) {
                //Get PlayerProfile of request sender
                PlayerProfile requestSenderProfile = gameState.getPlayerData()
                        .get(gameState.getNickName(session));
                //Verify Team exists or create one if it doesn't
                Team chosenTeam;
                //Get a list of teams with the same name as switchTo
                List<Team> validTeams = loopThroughTeams(gameState, switchTo);
                //Find a coalition from the joined Team
                chosenTeam = pickCoalition(validTeams);
                //Null check
                if (chosenTeam == null) {
                    chosenTeam = new Coalition(switchTo);
                }

                //Remove Player relations to Coalitions
                for (Coalition c : requestSenderProfile.getProfileCoalitions()) {
                    //Remove coalitions from player
                    PlayerProfile.removeCoalitionFromProfile(c, requestSenderProfile);
                    //Remove requestSender from any prior Coalitions
                    Coalition.removeProfileFromCoalition(c, requestSenderProfile);
                }


                //Add Team to players Team list
                requestSenderProfile.getJoinedTeams().add(chosenTeam);
                //Add player to Teams playerList
                chosenTeam.getPlayersOnTeam().add(requestSenderProfile);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public boolean isBuilt() {
        return false;
    }

    /*Loops through all player profiles in passed gameState and then loops through each profiles personal list of known
    * teams and after searching all teams in each profile returns a list of each team that shares the switchTo name*/
    private List<Team> loopThroughTeams(GameState gameState, String teamName){
        List<Team> foundTeams = new ArrayList<>();
        for( StringBuilder b : gameState.getPlayerData().keySet()){
            PlayerProfile temp = gameState.getPlayerData().get(b);
            for( Team t : temp.getJoinedTeams()){
                if(t.getTeamName().compareTo(switchTo) == 0){
                    foundTeams.add(t);
                }
            }
        }
        return foundTeams;
    }

    /** Returns the first Coalition present in the list, Returns null if none are Coalitions
     * @param passedTeams A list of removed
     *
     */
    private Coalition pickCoalition(List<Team> passedTeams){
        for(Team t : passedTeams){
            if( Coalition.class == t.getType()){
                return (Coalition) t;
            }
        }
        return null;
    }

    @Override
    public LoadState[] expectedLoadStates() {
        return new LoadState[] {LoadState.LOBBY};
    }

}
