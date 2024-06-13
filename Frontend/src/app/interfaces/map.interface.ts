export interface MapInterface {
    name: string;
    size: {
        rows: number;
        columns: number;
    };
    energy: number;
    altitude: number;
    spawn: [number, number];
    targets: number[][];
    obstacles: {
        type: string;
        coordinates: number[];
    }[];
}