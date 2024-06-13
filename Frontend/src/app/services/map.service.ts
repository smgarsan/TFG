import { Injectable } from '@angular/core';
import { SessionService } from './session.service';
import { MapInterface } from '../interfaces/map.interface';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  constructor(private sessionService: SessionService) { }

  async getMapInfo():Promise<MapInterface> {
    const session = await this.sessionService.getSession();
    const mapId = session.mapId;
    const result = await fetch("http://localhost:8080/maps/" + mapId);
    const resultJson = await result.json();
    return resultJson;
  }
}
