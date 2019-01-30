import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Tablename} from './tablename';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get(`http://localhost:8080/user/api/all/`) as Observable<any>;
  }
    getTables() {
    return this.http.get(`http://localhost:8080/api/info/`) as Observable<Tablename[]>;
  }
  postUser(form) {
    return this.http.post(`http://localhost:8080/user/api/add/`, form);
  }
  getTablesPola(name) {
    return this.http.get(`http://localhost:8080/api/table/` + name) as Observable<any>;
  }
  getData(name, sql) {
    return this.http.get(`http://localhost:8080/api/dane/sql/` + name + `/` + sql) as Observable<any>;
  }
  getAllData(name, sql) {
    return this.http.get(`http://localhost:8080/api/dane/sql/` + name + `/` + sql) as Observable<any>;
  }
  getSomeData(name, sql) {
    return this.http.get(`http://localhost:8080/api/dane/all/` + name + `/` + sql) as Observable<any>;
  }
}
