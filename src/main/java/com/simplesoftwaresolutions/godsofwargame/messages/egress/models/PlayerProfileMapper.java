package com.simplesoftwaresolutions.godsofwargame.messages.egress.models;

import com.simplesoftwaresolutions.godsofwargame.player.PlayerProfile;

public class PlayerProfileMapper {
    public static Model mapPlayerProfileToPlayerProfileModel(PlayerProfile profile) {
        return new PlayerProfileModel(profile.serverRole,
                profile.getUid(),
                profile.getJoinedTeams());
    }
}
