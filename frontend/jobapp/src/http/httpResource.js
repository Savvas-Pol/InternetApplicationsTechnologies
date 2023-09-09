import axios from "axios";
import authenticationService from "@/service/AuthenticationService";

const instance = axios.create({
  baseURL: process.env.VUE_APP_RESOURCE_SERVER,
  withCredentials: true,
  timeout: 10000,
});

// const requestHandler = (request) => {
//   if (isHandlerEnabled(request)) {
//     console.log("Request Interceptor", request);
//   }
//   return request;
// };

const errorHandler = (error) => {
  console.log("Error Interceptor", error);

  if (error.response) {
    if (error.response.status === 401 || error.response.status === 500) {
      authenticationService.performLogout();
    }
  }
  return Promise.reject({ ...error });
};


// instance.interceptors.request.use((request) => requestHandler(request));

// instance.interceptors.response.use(
//   (response) => successHandler(response),
//   (error) => errorHandler(error)
// );

export default instance;
