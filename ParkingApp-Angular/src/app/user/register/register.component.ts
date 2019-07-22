import { Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  exists=false;
  login(form) {
    this.userService.addUser(form.value.firstname,form.value.surname,form.value.email,form.value.password,form.value.id).subscribe(data =>{
      if(data=="done"){
        this.router.navigate(['/login']);
      }else{
        this.router.navigate(['/register']);
        this.exists=true;
      }
    })
  }
  constructor(private userService: UserService,private router: Router) { }

  ngOnInit() {
  }

}
