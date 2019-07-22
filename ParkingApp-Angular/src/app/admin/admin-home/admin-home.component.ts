import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  constructor(private router: Router,private userService:UserService) { }
  public logged=false;

  editUser(){
    this.router.navigate(['/charts']);
  }

  editParkingLot(){
    this.router.navigate(['/editParkingLot']);
  }

  ngOnInit() {
    if(localStorage.getItem('loggedIn')!=null && localStorage.getItem("location")){
        this.logged=true;
    }else{
      this.router.navigate(['/login']);
      this.logged=false;
      }
  }
}
