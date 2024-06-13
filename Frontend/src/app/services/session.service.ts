import { Injectable } from '@angular/core';
import { SessionInterface } from '../interfaces/session';
import { GlobalService } from './global.service';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private sessionData: SessionInterface | null = null;

  constructor(public globalService: GlobalService) { }

  async getSession():Promise<SessionInterface> {
    if (this.sessionData) {
      return this.sessionData;
    } else {
      const result = await fetch("http://localhost:8081/session/" + this.globalService.sessionId);
      const resultJson = await result.json();

      return resultJson;
    }
  }
}
