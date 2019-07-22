import { Component, OnInit } from '@angular/core';
import { ParkingLotService } from 'src/app/services/parkinglot.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-parking-lot',
  templateUrl: './add-parking-lot.component.html',
  styleUrls: ['./add-parking-lot.component.css']
})
export class AddParkingLotComponent implements OnInit {

  constructor(private parkingLotService:ParkingLotService,private router: Router,private userService:UserService) { }


  add(form){
    this.parkingLotService.addParkingLot(form.value.name,form.value.town,form.value.latitude,form.value.longitude,form.value.spotNr,form.value.location,form.value.price).subscribe((data=>{
      if(data=="done"){
        this.router.navigate(['/searchParkingLot'])
      }
    }))
  }

  ngOnInit() {
    if(localStorage.getItem("loggedIn")!=null){
    }else{
      this.router.navigate(['/login']);
      }
  }

}
