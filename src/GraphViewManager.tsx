import React from 'react';
import {
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    requireNativeComponent, ViewProps,
} from 'react-native';

export const GraphViewManager = requireNativeComponent<{}>("GraphViewManager");

interface GraphViewProps extends ViewProps {
    assembleData: {
        labels: string[];
        visibleX: number;
        data: IData[];
    }
}

interface IData {
    x: number;
    y: number;
}

type GraphViewManagerProps = GraphViewProps;

export const GraphView: React.FC<GraphViewManagerProps> = (props) => {
    //@ts-ignore
    return <GraphViewManager {...props} />
}