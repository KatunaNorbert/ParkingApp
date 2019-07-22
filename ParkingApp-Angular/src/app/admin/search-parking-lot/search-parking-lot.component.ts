import { Component, OnInit } from '@angular/core';
import { ParkingLot } from 'src/app/models/parkingLot';
import { ParkingLotService } from 'src/app/services/parkinglot.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-search-parking-lot',
  templateUrl: './search-parking-lot.component.html',
  styleUrls: ['./search-parking-lot.component.css']
})
export class SearchParkingLotComponent implements OnInit {

  public pLots: ParkingLot[];
  getpLots=false;
  pLotFound=true;
  constructor(private parkingLotService:ParkingLotService,private router: Router,private userService:UserService) { }

  searchParkingLot(searchForm){
    this.parkingLotService.getParkingLotsByLocation(searchForm.value.search).subscribe(data=>{
      if(data!=null){
        this.pLots=data;
        this.getpLots=true;
      }else{
        this.pLotFound=false;
      }
    });
  }

  setValues(p,form){
    localStorage.setItem("location",p.location);
    this.router.navigate(['/adminhome']);
  }

  ngOnInit() {
    if(localStorage.getItem("loggedIn")!=null){
    }else{
      this.router.navigate(['/login']);
      }
  }

}
