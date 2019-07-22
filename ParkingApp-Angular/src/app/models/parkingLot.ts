export class ParkingLot{
    name: string;
    town: string;
    latitude: string;
    longitude: string;
    spot_number: number;
    location: string;
    price: number;


    public ParkingLot(){
        this.name="";
        this.town="";
        this.latitude="";
        this.longitude="";
        this.spot_number=0;
        this.location="";
        this.price=0;
    }
}