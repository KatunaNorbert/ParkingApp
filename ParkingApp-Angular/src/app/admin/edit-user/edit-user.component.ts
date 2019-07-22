import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  public users: User[];
  constructor(private userService:UserService,private router:Router) { }
  getUsers=false;
  userFound=true;

  searchUser(searchForm){
    this.userService.getUsersByEmail(searchForm.value.search).subscribe(data=>{
      if(data!=null){
      this.users=data;
      this.getUsers=true;
      }else{
        this.userFound=false;
      }
    });
  }

  setValues(u,form){
    form.setValue({
      firstname: u.name,
      surname: u.surname,
      email: u.email,
      password: u.password
  });
  }

  update(form){
    this.userService.update(form.value.firstname,form.value.surname,form.value.email,form.value.password,form.value.id).subscribe((data =>{
      if(data=="done"){
        this.router.navigate(['/adminhome'])
      }
    }))
  }

  delete(form){
    this.userService.delete(form.value.email).subscribe((data =>{
      if(data=="done"){
        this.router.navigate(['/adminhome'])
      }
    }))
  }

  ngOnInit() {
    if(localStorage.getItem("loggedIn")==null){
      this.router.navigate(['/login']);
      }

  }

}
