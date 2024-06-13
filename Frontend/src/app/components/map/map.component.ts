import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { MapInterface } from '../../interfaces/map.interface';
import { CommonModule } from '@angular/common';
import { SSEData } from '../../interfaces/sse-data.interface';
import { SseService } from '../../services/sse.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-map',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './map.component.html',
  styleUrl: './map.component.scss'
})
export class MapComponent {
  
  @Input() mapData!: MapInterface;
  tileSize!: number;
  rows!: number;
  columns!: number;
  containerSize: number = 500;
  obstacles!: MapInterface["obstacles"];
  rowsArray!: number[];
  columnsArray!: number[];
  @Input() currentPosition!: number[];
  lastPosition: number[] = [];
  targets!: number[][];
  tiles: { row: number, col: number, color: string }[] = [];
  private subscription!: Subscription;

  constructor(private sseService: SseService, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.rows = this.mapData.size.rows;
    this.columns = this.mapData.size.columns;
    this.tileSize = this.containerSize / this.rows;
    this.obstacles = this.mapData.obstacles;
    this.rowsArray = Array(this.mapData.size.rows).fill(0).map((x, i) => i);
    this.columnsArray = Array(this.mapData.size.columns).fill(0).map((x, i) => i);
    this.targets = this.mapData.targets;
    this.initializeTiles();
    this.lastPosition = this.currentPosition;
    this.updateMap();
  }

  updateMap() {
    this.subscription = this.sseService.getMessageSubject().subscribe((update: SSEData) => {
      this.changeTileColor(this.lastPosition[0], this.lastPosition[1], '#FFFEFF');
      this.lastPosition = this.currentPosition;
      this.currentPosition = update.currentPosition;
      this.changeTileColor(this.currentPosition[0], this.currentPosition[1], '#92AAC7');
      this.cdr.detectChanges();
    });
  }

  initializeTiles() {
    for (let row = 0; row < this.rows; row++) {
      for (let col = 0; col < this.columns; col++) {
        this.tiles.push({ row, col, color: '#FFFEFF' });
      }
    }

    this.obstacles.forEach(obstacle => {
      const index = obstacle.coordinates[0] * this.columns + obstacle.coordinates[1];
      this.tiles[index].color = '#000000';
    });

    //const firstTileIndex = 0;
    //this.tiles[firstTileIndex].color = '#000000';

    this.changeTileColor(this.currentPosition[0], this.currentPosition[1], '#92AAC7');
    for (let i = 0; i < this.targets.length; i++) {
      this.changeTileColor(this.targets[i][0], this.targets[i][1], '#ED5752');
  }  
  }

  changeTileColor(row: number, col: number, color: string) {
    const index = (row * this.columns) + col;
    this.tiles[index].color = color;
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
