package com.oaktwister.services;

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
            String PLATFORM = "/images/cyberpunk/platform.png";
        }

        interface HelpSupport {
            String INFORMATION = "/images/help-support/information.png";
            String KEY = "/images/help-support/key.png";
            String SETTINGS = "/images/help-support/settings.png";
        }

        interface Placeholders {
            String IMAGE = "/images/placeholders/image.png";
        }

        interface Social {
            String FACEBOOK = "/images/social/facebook.png";
        }

        interface SocialNetwork {
            String ADD = "/images/social-network/add.png";
            String FAVOURITE = "/images/social-network/favourite.png";
            String FRIENDS = "/images/social-network/friends.png";
            String HOME = "/images/social-network/home.png";
            String MESSAGE = "/images/social-network/message.png";
            String NEWSFEED = "/images/social-network/newsfeed.png";
            String POLL = "/images/social-network/poll.png";
            String SHARE = "/images/social-network/share.png";
            String TAG = "/images/social-network/tag.png";
            String USER = "/images/social-network/user.png";
        }

        interface Vikings {
            String OAK = "/images/vikings/oak.png";
        }

    }

    interface Styles {

        interface Controls {
            String IMAGE_BUTTON_BOX = "/styles/controls/image-button-box.css";
        }

        interface Roots {
            String MAIN = "/styles/roots/landing.css";
            String LANDING = "/styles/roots/landing.css";
        }

    }

    interface Views {

        interface Controls {
            String ACCOUNT_CELL = "/views/controls/account-cell.fxml";
            String IDENTITY_CELL = "/views/controls/identity-cell.fxml";
            String IMAGE_BUTTON_BOX = "/views/controls/image-button-box.fxml";
            String PLATFORM_PANE = "/views/controls/platform-pane.fxml";
        }

        interface Layouts {
            String ACCOUNTS_PANE = "/views/layouts/accounts-pane.fxml";
            String IDENTITIES_PANE = "/views/layouts/identities-pane.fxml";
            String PLATFORMS_PANE = "/views/layouts/platforms-pane.fxml";
        }

        interface Roots {
            String LANDING = "/views/roots/landing.fxml";
            String MAIN = "/views/roots/main.fxml";
        }

    }

}
