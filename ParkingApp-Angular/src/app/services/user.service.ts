import { User } from '../models/user';
import { ConfigService } from '../Config/config.service';
import { Injectable, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class UserService{

  constructor(private http: HttpClient, private configService : ConfigService) { 
    this.baseUrl = configService._apiURI;
    this.userUrl = this.baseUrl + "user/";
  }

  baseUrl: string = "";
  userUrl;

  public getUser(email: string){
    return this.http.get<User>(this.userUrl + "getUser/"+email);
  }
  public getUsers(): Observable<User[]>{
    return this.http.get<User[]>(this.userUrl + "getAll");
  }

  public getUsersByEmail(email: string): Observable<User[]>{
    return this.http.get<User[]>(this.userUrl + "getUsersByEmail/"+email);
  }

  /*
  public getLoggedUser(){
    return this.http.get<User>(this.userUrl + "getLoggedUser");
  }*/

  public logStatus(){
    return this.http.get(this.userUrl + "logStatus", {responseType: 'text'});
  }

  public logIn(email: string, password: string){
    return this.http.get(this.userUrl + "logIn/" + email + "/" + password, {responseType: 'text'});
  }

  public logOut(){
    return this.http.get(this.userUrl + "logOut", {responseType: 'text'});
  }

  public addUser(name: string, surname: string, email: string, password: string, id: string){
    return this.http.get(this.userUrl + "add/" + name + "/" + surname + "/" + email + "/" + password + "/" + id, {responseType: 'text'});
  }

  public delete(email: string){
    return this.http.delete(this.userUrl + "delete/" + email,{responseType: 'text'})
  }

  public update(name,surname,email,password,id: string){
    return this.http.get(this.userUrl + "update/" + name + "/" + surname + "/" + email + "/" + password + "/" + id, {responseType: 'text'})
  }
}
