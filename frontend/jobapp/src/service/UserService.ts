import { Vue } from "vue-property-decorator";
import {
  FIND_ALL_USERS_URL,
  CHANGE_PASSWORD,
  CHANGE_EMAIL,
  FIND_USER_BY_ID,
  INSERT_EDUCATION,
  INSERT_EXPERIENCE,
  INSERT_SKILL,
  FIND_FRIENDS,
} from "@/axios/api";

class UserService {
  public list() {
    return Vue.axios.get(FIND_ALL_USERS_URL);
  }

  public listFriends(id: number) {
    return Vue.axios.get(FIND_FRIENDS + id);
  }

  public getUserDetails(id: number) {
    return Vue.axios.get(FIND_USER_BY_ID + id);
  }

  public changePassword(
    email: string,
    oldpassword: string,
    newpassword: string
  ) {
    const body = {
      email: email,
      oldpassword: oldpassword,
      newpassword: newpassword,
    };

    return Vue.axios.post(CHANGE_PASSWORD, body);
  }

  public changeEmail(email: string, newemail: string) {
    const body = {
      email: email,
      newemail: newemail,
    };

    return Vue.axios.post(CHANGE_EMAIL, body);
  }

  public addExperience(data: Record<string, unknown>) {
    return Vue.axios.post(INSERT_EXPERIENCE, data);
  }

  public addEducation(data: Record<string, unknown>) {
    return Vue.axios.post(INSERT_EDUCATION, data);
  }

  public addSkill(data: Record<string, unknown>) {
    return Vue.axios.post(INSERT_SKILL, data);
  }
}

export default new UserService();
