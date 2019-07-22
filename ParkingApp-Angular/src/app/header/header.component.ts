import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public logged=true;
  
  log(){
    if(localStorage.getItem("loggedIn")!=null){
      localStorage.clear();
      this.logged=false;
      this.router.navigate(['/home']);
    }else{
      this.router.navigate(['/login']);
    }
  
  }

  constructor(private userService: UserService,private router: Router) { }

  ngOnInit() {
      if(localStorage.getItem("loggedIn")!=null){
        this.logged=true;
    }else{
      this.logged=false;
    }
  }

}
