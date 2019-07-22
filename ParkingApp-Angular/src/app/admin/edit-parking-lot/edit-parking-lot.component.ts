import { ParkingLot } from './../../models/parkingLot';
import { ParkingLotService } from './../../services/parkinglot.service';
import { Component, OnInit} from '@angular/core';
import { Query} from '@syncfusion/ej2-data'
import { FilteringEventArgs } from '@syncfusion/ej2-dropdowns';
import { EmitType } from '@syncfusion/ej2-base';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-edit-parking-lot',
  templateUrl: './edit-parking-lot.component.html',
  styleUrls: ['./edit-parking-lot.component.css']
})
export class EditParkingLotComponent implements OnInit {

  public pLot: ParkingLot;
  getpLots=false;
  pLotFound=true;
  constructor(private parkingLotService:ParkingLotService,private router: Router,private userService:UserService) { }

  update(form){
    this.parkingLotService.updateParkingLot(form.value.name,form.value.town,form.value.latitude,form.value.longitude,form.value.spotNr,form.value.location,form.value.price).subscribe((data =>{
      if(data=="done"){
        this.router.navigate(['/adminhome'])
      }
    }))
  }

  delete(form){
    this.parkingLotService.deleteParkinLot(form.value.location).subscribe((data)=>{
      if(data=="done"){
        this.router.navigate(['/adminhome'])
      }
    })
  }

  ngOnInit() {
      if(localStorage.getItem("loggedIn")!=null && localStorage.getItem("location")){
        this.parkingLotService.getParkingLotByLocation(localStorage.getItem("location")).subscribe((data: ParkingLot) => {this.pLot=data});
    }else{
      this.router.navigate(['/login']);
      }
  }

}
