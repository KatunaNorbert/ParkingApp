import { Router } from '@angular/router';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  constructor(private userService: UserService, private router:Router) { }
  public logged=true;

  log(){
    if(localStorage.getItem("loggedIn")!=null){
      localStorage.clear();
      this.logged=false;
      this.router.navigate(['/home']);
    }else{
      this.logged=true;
      this.router.navigate(['/login']);
    }
   
   }

  ngOnInit() {
    if(localStorage.getItem('loggedIn')!=null){
      this.logged=true;
    }else{
    this.logged=false;
    }
  }

}
