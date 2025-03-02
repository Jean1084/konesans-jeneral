import axios from "axios";
import ToastService from "./ToastService";
import UserService from "./UserService";

const Axios = axios.create({
  baseURL: '/api',
});

class HttpClient {

  static INSTANCE = new HttpClient();

  get<T>(url: string) {
    return Axios.get<T>(url, this.getAuthorizationHeader());
  }

  post(url: string, data: any) {
    return Axios.post(url, data, this.getAuthorizationHeader());
  }

  put(url: string, data: any) {
    return Axios.put(url, data, this.getAuthorizationHeader());
  }

  delete<T>(url: string) {
    return Axios.delete<T>(url, this.getAuthorizationHeader());
  }

  import(url: string, file: File, name: string) {
    const formData = new FormData();
    formData.append(name, file);
    return Axios.post(url, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
        'Authorization': UserService.getAccessToken()
      }
    });
  }

  export(url: string, filename: string) {
    Axios.get(url, {
      responseType: 'blob',
      headers: {
        'Authorization': UserService.getAccessToken()
      }
    }).then(response => {
      // code reference: https://stackoverflow.com/questions/3916191/download-data-url-file
      const type = response.headers['content-type'];
      const blob = new Blob([response.data], { type: type });
      const link = document.createElement('a');
      link.href = URL.createObjectURL(blob);
      link.download = filename;
      link.click();
    });
  }

  handleApiError = (error: any) => {
    console.error(error);
    ToastService.error('Erreur lors de l\'appel backend');
  }

  private getAuthorizationHeader() {
    return {
      headers: {
        'Authorization': UserService.getAccessToken()
      }
    }
  }

}

export default HttpClient.INSTANCE;

