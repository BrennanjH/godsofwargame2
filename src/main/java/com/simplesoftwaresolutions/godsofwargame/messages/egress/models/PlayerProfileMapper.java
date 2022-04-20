package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.simplesoftwaresolutions.godsofwargame.messages.egress.BusinessObject;
import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

public class PlayerProfileMapper implements Mapper{
    @Deprecated
    public static Model mapPlayerProfileToPlayerProfileModel(PlayerProfile profile) {
        return new PlayerProfileModel(profile.getServerRole(),
                profile.getUid(),
                profile.getJoinedTeams());
    }

    @Override
    public Model map(BusinessObject profile) {
        PlayerProfile playerProfile = (PlayerProfile) profile;
        return new PlayerProfileModel(playerProfile.getServerRole(),
                playerProfile.getUid(),
                playerProfile.getJoinedTeams());
    }
}
