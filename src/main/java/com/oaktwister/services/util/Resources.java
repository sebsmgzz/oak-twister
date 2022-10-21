package com.oaktwister.services.util;

public interface Resources {

    interface Strings {
        String IDENTITIES_BUTTON_TEXT = "Identities";
        String PLATFORMS_BUTTON_TEXT = "Platforms";
        String ACCOUNTS_BUTTON_TEXT = "Accounts";

        interface App {
            String TITLE = "OakTwister";
        }

    }

    interface Images {

        interface Cyberpunk {
            String PLATFORM = "/com/oaktwister/images/cyberpunk/platform.png";
        }

        interface FontAwesome {
            String FINGERPRINT_SOLID = "/com/oaktwister/images/font-awesome/fingerprint-solid.png";
            String HOUSE_SOLID = "/com/oaktwister/images/font-awesome/house-solid.png";
            String LAYER_GROUP_SOLID = "/com/oaktwister/images/font-awesome/layer-group-solid.png";
            String USER_SOLID = "/com/oaktwister/images/font-awesome/user-solid.png";
            String USERS_SOLID = "/com/oaktwister/images/font-awesome/users-solid.png";
        }

        interface HelpSupport {
            String INFORMATION = "/com/oaktwister/images/help-support/information.png";
            String KEY = "/com/oaktwister/images/help-support/key.png";
            String SETTINGS = "/com/oaktwister/images/help-support/settings.png";
        }

        interface Placeholders {
            String IMAGE = "/com/oaktwister/images/placeholders/image.png";
        }

        interface Social {
            String FACEBOOK = "/com/oaktwister/images/social/facebook.png";
        }

        interface SocialNetwork {
            String ADD = "/com/oaktwister/images/social-network/add.png";
            String FAVOURITE = "/com/oaktwister/images/social-network/favourite.png";
            String FRIENDS = "/com/oaktwister/images/social-network/friends.png";
            String HOME = "/com/oaktwister/images/social-network/home.png";
            String MESSAGE = "/com/oaktwister/images/social-network/message.png";
            String NEWSFEED = "/com/oaktwister/images/social-network/newsfeed.png";
            String POLL = "/com/oaktwister/images/social-network/poll.png";
            String SHARE = "/com/oaktwister/images/social-network/share.png";
            String TAG = "/com/oaktwister/images/social-network/tag.png";
            String USER = "/com/oaktwister/images/social-network/user.png";
        }

        interface Vikings {
            String OAK = "/com/oaktwister/images/vikings/oak.png";
        }

    }

    interface Styles {

        interface Controls {
            String ACCOUNT_BOX = "/com/oaktwister/styles/accounts/account-box.css";
            String IDENTITY_CELL = "/com/oaktwister/styles/identities/identity-pane.css";
            String IMAGE_BUTTON_BOX = "/com/oaktwister/styles/laterals/image-button-box.css";
            String PLATFORM_PANE = "/com/oaktwister/styles/platforms/platform-pane.css";
        }

        interface Layouts {
            String ACCOUNTS_PANE = "/com/oaktwister/styles/accounts/accounts-pane.css";
            String IDENTITIES_PANE = "/com/oaktwister/styles/identities/identities-pane.css";
            String PLATFORMS_PANE = "/com/oaktwister/styles/platforms/platforms-pane.css";
        }

        interface Roots {
            String MAIN = "/com/oaktwister/styles/landing/landing.css";
            String LANDING = "/com/oaktwister/styles/landing/landing.css";
        }

    }

    interface Views {

        interface Controls {
            String ACCOUNT_CELL = "/com/oaktwister/views/accounts/account-pane.fxml";
            String IDENTITY_CELL = "/com/oaktwister/views/identities/identity-pane.fxml";
            String IMAGE_BUTTON_BOX = "/com/oaktwister/views/laterals/image-button-box.fxml";
            String PLATFORM_PANE = "/com/oaktwister/views/platforms/platform-pane.fxml";
        }

        interface Layouts {
            String ACCOUNTS_PANE = "/com/oaktwister/views/accounts/accounts-pane.fxml";
            String IDENTITIES_PANE = "/com/oaktwister/views/identities/identities-pane.fxml";
            String PLATFORMS_PANE = "/com/oaktwister/views/platforms/platforms-pane.fxml";
        }

        interface Roots {
            String LANDING = "landing.fxml";
            String MAIN = "/com/oaktwister/views/main/main.fxml";
        }

    }

}
