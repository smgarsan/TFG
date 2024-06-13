import { Injectable } from '@angular/core';
import { SSEData } from '../interfaces/sse-data.interface';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SseService {
  private eventSource: EventSource | undefined;
  messages: SSEData[] = [];
  private messageSubject: Subject<SSEData> = new Subject<SSEData>();

  constructor() { }

  connectToSSE(sessionId: string) {
    const url = `http://localhost:8081/sse/connect/${sessionId}`;
    this.eventSource = new EventSource(url);

    this.eventSource.onmessage = (event) => {
      const eventData: SSEData = JSON.parse(event.data);
      this.messages.push(eventData);
      this.messageSubject.next(eventData);
    };

    this.eventSource.onerror = (error) => {
      console.error('EventSource failed:', error);
      this.eventSource?.close();
    };
  }

  getMessageSubject(): Subject<SSEData> {
    return this.messageSubject;
  }

  closeConnection() {
    this.eventSource?.close();
  }
}
