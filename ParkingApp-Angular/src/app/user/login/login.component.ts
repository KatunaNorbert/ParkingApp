import { Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ReservationService } from './../../services/reservation.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  LogError=false;
  
  login(form) {
    this.userService.logIn(form.value.email,form.value.password).subscribe(data =>{
      if(data=="user"){
        localStorage.setItem('loggedIn',form.value.email);
        this.router.navigate(['/home']);
      }else if(data=="admin"){
        localStorage.setItem('loggedIn',form.value.email);
        this.router.navigate(['/searchParkingLot']);
      }else{
        this.router.navigate(['/login']);
        this.LogError=true;
      }
    })
  }

  constructor(private resService: ReservationService,private userService: UserService,private router: Router) { }

  ngOnInit() {

  }

}
