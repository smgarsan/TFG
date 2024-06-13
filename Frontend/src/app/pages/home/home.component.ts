import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { DataCardComponent } from "../../components/data-card/data-card.component";
import { SessionService } from '../../services/session.service';
import { CommonModule } from '@angular/common';
import { MapService } from '../../services/map.service';
import { MapComponent } from "../../components/map/map.component";
import { MapInterface } from '../../interfaces/map.interface';
import { SessionInterface } from '../../interfaces/session';
import { SseService } from '../../services/sse.service';
import { Subscription } from 'rxjs';
import { SSEData } from '../../interfaces/sse-data.interface';
import { GlobalService } from '../../services/global.service';

@Component({
    selector: 'app-home',
    standalone: true,
    templateUrl: './home.component.html',
    styleUrl: './home.component.scss',
    imports: [DataCardComponent, CommonModule, MapComponent]
})
export class HomeComponent implements OnInit {
    sessionData!: SessionInterface;
    mapData!: MapInterface;
    private subscription!: Subscription;

    constructor(private sessionService: SessionService, 
        private mapService: MapService,
        private sseService: SseService,
        public globalService: GlobalService,
        private cdr: ChangeDetectorRef) {}

    ngOnInit(): void {
        this.loadData();
        this.sseService.connectToSSE(this.globalService.sessionId);
        this.changeEnergy();
    }

    async loadData() {
        this.sessionData = await this.sessionService.getSession();
        this.mapData = await this.mapService.getMapInfo();
    }

    changeEnergy() {
        this.subscription = this.sseService.getMessageSubject().subscribe((update: SSEData) => {
            this.sessionData.energy = update.energy;
            this.sessionData.currentPosition = update.currentPosition;
            this.cdr.detectChanges();
        });
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
    }
}