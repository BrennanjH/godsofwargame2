package com.simplesoftwaresolutions.godsofwargame.messages.services;

import com.simplesoftwaresolutions.godsofwargame.game.GameState;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.AbstractReturnModel;
import com.simplesoftwaresolutions.godsofwargame.messages.egress.CustomPayload;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;
import com.simplesoftwaresolutions.godsofwargame.player.ServerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayerViewService {
    @Autowired
    GameState gameState;
    public Map<String, AbstractReturnModel> createUniqueMessageForEachClient(AbstractReturnModel returnableData, List<WebSocketSession> clients){
        //For each player calculate their viewable area
        Map<String, AbstractReturnModel> clientPayloads = new HashMap<>();
        for (WebSocketSession session :
                clients) {
            //Identify players role, Spectators see all and know all, Clients have various viewing behaviors
            PlayerProfile playerData = gameState.getPlayerFromSession(session);
            if(playerData.getServerRole() == ServerRole.SPECTATOR){
                //Send all data
            } else if(playerData.getServerRole() == ServerRole.GAME_MEMBER) {

                AbstractReturnModel playerViewModel = new CustomPayload();
                //Identify Players Data
                //TODO
                //Identify Players teammates data
                //TODO
                //Identify Players unit vision (fog of war)
                //TODO

                //place viewmodel inside the hashmap
                clientPayloads.put(session.getId(), playerViewModel);
            } else {
                //Non-roles means that the player is likely a lobby-role, this means that the payload should probably
                //not be altered as all lobby members are the same in data rights
                 clientPayloads.put(session.getId(), returnableData);
            }
        }
        return clientPayloads;
    }
}
