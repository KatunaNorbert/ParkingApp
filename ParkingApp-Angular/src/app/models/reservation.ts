import { ParkingLot } from './parkingLot';
import { User } from './user';
export class Reservation{
    id: Number;
    start: string;
    end: string;
    pLot: ParkingLot;
    user: User;

    public Reservation(){
        this.id=0;
        this.start="";
        this.end="";
        this.pLot=null;
        this.user=null;
    }
}