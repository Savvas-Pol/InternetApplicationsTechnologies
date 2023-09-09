const BASE_URL = "https://localhost:8443";

//AUTHORIZATION
export const LOGIN_URL = BASE_URL + "/jobapp/auth/login";
export const LOGOUT_URL = BASE_URL + "/jobapp/auth/logout";
export const WHO_AM_I = BASE_URL + "/jobapp/auth/me";

//ADMIN
export const DOWNLOAD_XML = BASE_URL + "/jobapp/api/export/xml";
export const DOWNLOAD_JSON = BASE_URL + "/jobapp/api/export/json";

//USER
export const REGISTRATION_URL = BASE_URL + "/jobapp/auth/register";
export const FIND_ALL_USERS_URL = BASE_URL + "/jobapp/api/user/all";
export const FIND_USER_BY_ID = BASE_URL + "/jobapp/api/user/id/";
export const FIND_USER_BY_EMAIL = BASE_URL + "/jobapp/api/user/email/";
export const FIND_USER_BY_FIRSTNAME = BASE_URL + "/jobapp/api/user/firstname/";
export const FIND_USER_BY_LASTNAME = BASE_URL + "/jobapp/api/user/lastname/";
export const FIND_USER_BY_PHONE = BASE_URL + "/jobapp/api/user/phone/";
export const FIND_USER_BY_PHOTO = BASE_URL + "/jobapp/api/user/photo/";
export const CHANGE_PASSWORD = BASE_URL + "/jobapp/api/user/password/";
export const CHANGE_EMAIL = BASE_URL + "/jobapp/api/user/email/";
export const FIND_FRIENDS = BASE_URL + "/jobapp/api/user/network/";

//ROLE
export const FIND_ROLE_BY_ID = BASE_URL + "/jobapp/api/role/id/";
export const FIND_ROLE_BY_DESCRIPTION =
  BASE_URL + "/jobapp/api/role/description/";

//EXPERIENCE
export const FIND_ALL_EXPERIENCES = BASE_URL + "/jobapp/api/experience/all";
export const INSERT_EXPERIENCE = BASE_URL + "/jobapp/api/experience/create";
export const FIND_EXPERIENCE_BY_ID = BASE_URL + "/jobapp/api/experience/id/";
export const FIND_EXPERIENCE_BY_TITLE =
  BASE_URL + "/jobapp/api/experience/title/";

//EDUCATION
export const FIND_ALL_EDUCATIONS = BASE_URL + "/jobapp/api/education/all";
export const INSERT_EDUCATION = BASE_URL + "/jobapp/api/education/create";
export const FIND_EDUCATION_BY_ID = BASE_URL + "/jobapp/api/education/id/";
export const FIND_EDUCATION_BY_TITLE =
  BASE_URL + "/jobapp/api/education/title/";

//CONVERSATION
export const FIND_ALL_CONVERSATIONS = BASE_URL + "/jobapp/api/conversation/all";
export const INSERT_CONVERSATION = BASE_URL + "/jobapp/api/conversation/create";
export const FIND_CONVERSATION_BY_ID =
  BASE_URL + "/jobapp/api/conversation/id/";
export const FIND_CONVERSATION_BY_DATE =
  BASE_URL + "/jobapp/api/conversation/date/";

//MESSAGE
export const FIND_ALL_MESSAGES = BASE_URL + "/jobapp/api/message/all";
export const INSERT_MESSAGE = BASE_URL + "/jobapp/api/message/create";
export const FIND_MESSAGE_BY_ID = BASE_URL + "/jobapp/api/message/id/";
export const FIND_MESSAGE_BY_CONTENT =
  BASE_URL + "/jobapp/api/message/content/";

//SKILL
export const FIND_ALL_SKILLS = BASE_URL + "/jobapp/api/skill/all";
export const INSERT_SKILL = BASE_URL + "/jobapp/api/skill/create";
export const FIND_SKILL_BY_ID = BASE_URL + "/jobapp/api/skill/id/";
export const FIND_SKILL_BY_DESCRIPTION =
  BASE_URL + "/jobapp/api/skill/description/";

//NOTIFICATION
export const FIND_ALL_NOTIFICATIONS = BASE_URL + "/jobapp/api/notification/all";
export const INSERT_NOTIFICATION = BASE_URL + "/jobapp/api/notification/create";
export const FIND_NOTIFICATION_BY_ID =
  BASE_URL + "/jobapp/api/notification/id/";
export const FIND_NOTIFICATION_BY_DATE =
  BASE_URL + "/jobapp/api/notification/date/";
export const FIND_NOTIFICATION_BY_CONTENT =
  BASE_URL + "/jobapp/api/notification/content/";

//TIMELINE
export const FIND_ALL_TIMELINES = BASE_URL + "/jobapp/api/timeline/all";
export const INSERT_TIMELINE = BASE_URL + "/jobapp/api/timeline/create";
export const FIND_TIMELINE_BY_ID = BASE_URL + "/jobapp/api/timeline/id/";
export const FIND_TIMELINE_BY_DETAILS =
  BASE_URL + "/jobapp/api/timeline/details/";

//AD
export const FIND_ALL_ADS = BASE_URL + "/jobapp/api/ad/all";
export const INSERT_AD = BASE_URL + "/jobapp/api/ad/create";
export const FIND_AD_BY_ID = BASE_URL + "/jobapp/api/ad/id/";
export const FIND_ADS_VIEWED_BY_USER = BASE_URL + "/jobapp/api/ad/user/";
export const FIND_MY_ADS = BASE_URL + "/jobapp/api/ad/author/";
export const FIND_FRIENDS_ADS = BASE_URL + "/jobapp/api/ad/friends/";
export const FIND_APPLIED_ADS = BASE_URL + "/jobapp/api/ad/applied/";
export const FIND_RECOMMENDED_ADS = BASE_URL + "/jobapp/api/ad/recommended/";

//ARTICLE
export const FIND_ALL_ARTICLES = BASE_URL + "/jobapp/api/article/all";
export const INSERT_ARTICLE = BASE_URL + "/jobapp/api/article/create";
export const FIND_ARTICLE_BY_ID = BASE_URL + "/jobapp/api/article/id/";
export const FIND_ARTICLES_LIKED_BY_USER =
  BASE_URL + "/jobapp/api/article/user/";
export const FIND_MY_ARTICLES = BASE_URL + "/jobapp/api/article/author/";
export const FIND_FRIENDS_ARTICLES = BASE_URL + "/jobapp/api/article/friends/";
export const FIND_FRIENDS_LIKED_ARTICLES =
  BASE_URL + "/jobapp/api/article/liked/";
export const LIKE_ARTICLE = BASE_URL + "/jobapp/api/article/like/";
export const FIND_RECOMMENDED_ARTICLES =
  BASE_URL + "/jobapp/api/article/recommended/";

//SCOPE
export const FIND_SCOPE_BY_ID = BASE_URL + "/jobapp/api/scope/id/";

//CONNECTIONREQUEST
export const FIND_ALL_REQUESTS =
  BASE_URL + "/jobapp/api/connection_request/all";
export const INSERT_REQUEST =
  BASE_URL + "/jobapp/api/connection_request/create";
export const FIND_REQUEST_BY_ID =
  BASE_URL + "/jobapp/api/connection_request/id/";

//APPLICATION
export const FIND_ALL_APPLICATIONS = BASE_URL + "/jobapp/api/application/all";
export const INSERT_APPLICATION = BASE_URL + "/jobapp/api/application/create";
export const FIND_APPLICATION_BY_ID = BASE_URL + "/jobapp/api/application/id/";

//COMMENT
export const FIND_ALL_COMMENTS = BASE_URL + "/jobapp/api/comment/all";
export const INSERT_COMMENT = BASE_URL + "/jobapp/api/comment/create";
export const FIND_COMMENT_BY_ID = BASE_URL + "/jobapp/api/comment/id/";
