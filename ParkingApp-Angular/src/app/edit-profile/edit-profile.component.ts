import { User } from './../models/user';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  u: User=new User;

  constructor(private userService:UserService,private router:Router) { }

  update(form){
    this.userService.update(this.u.name,this.u.surname,this.u.email,this.u.password,this.u.id).subscribe((data =>{
      if(data=="done"){
        this.router.navigate(['/myprofile'])
      }
    }))
  }

  ngOnInit() {
    if(localStorage.getItem("loggedIn")==null){
      this.router.navigate(['/login']);
    }else{
      this.userService.getUser(localStorage.getItem("loggedIn")).subscribe((data: User) =>{
        this.u.name=data.name;
          this.u.surname=data.surname;
          this.u.email=data.email;
          this.u.password=data.password;
          this.u.id=data.id;
      })
    }
  }

}
